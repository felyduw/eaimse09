using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;
using System.Threading;
using System.Xml;
using System;
using System.IO;

namespace EAI.A4.IntegrationWrapper_CameraSummaryXML
{
	public partial class FormMainIWCameraSummaryXml : Form
	{
		MessageQueue iwCameraSummaryXmlInboxQueue = null;
		MessageQueue iwCameraSummaryXmlOutboxQueue = null;

		public FormMainIWCameraSummaryXml()
		{
			InitializeComponent();
			InitializeMessageQueues();
			InitializeThreads();
		}

		private void InitializeMessageQueues()
		{
			iwCameraSummaryXmlInboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraSummaryXmlInboxQueue);
			iwCameraSummaryXmlInboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(XmlDocument) }));
			iwCameraSummaryXmlInboxQueue.MessageReadPropertyFilter.SetAll();
			iwCameraSummaryXmlOutboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraSummaryXmlOutboxQueue);
		}

		private void InitializeThreads()
		{
			ThreadStart job1 = new ThreadStart(DoWork);
			Thread thread1 = new Thread(job1);
			thread1.Start();
		}

		private void DoWork()
		{
			int nrProcessedMsgs = 0;
			while (true)
			{
				// receives message
				System.Messaging.Message incomingMsg = iwCameraSummaryXmlInboxQueue.Receive();
				incomingMsg.CorrelationId = incomingMsg.Id;
				XmlDocument msg = (XmlDocument)incomingMsg.Body;
				// writes the msg to a new XML file
				string filename = "IWCameraSummary" + incomingMsg.ArrivedTime.ToString("yyyyMMddHHmmssffff") + ".xml";
				XmlTextWriter writer = new XmlTextWriter(filename, new System.Text.UTF8Encoding());
				writer.Formatting = Formatting.Indented;
				msg.WriteTo(writer);
				writer.Close();
				// summarizes the information and produces a new XML file by calling the original CameraSummaryXML java application
				string callJavaCameraSummaryXMLResult = CallJavaCameraSummaryXML(filename);
				// reads the XML from the newly created file

				// send the result XML to the outbox message queue
				iwCameraSummaryXmlOutboxQueue.Send(callJavaCameraSummaryXMLResult);
				// write to the form
				if (labelNrProcessedMsgs1.InvokeRequired && labelLastMsg1.InvokeRequired)
				{
					SetTextCallback d = new SetTextCallback(SetLabels1);
					this.Invoke(d, new object[] { ++nrProcessedMsgs, msg.InnerXml.Substring(0, 100), incomingMsg.CorrelationId });
				}
			}
		}

		private string CallJavaCameraSummaryXML(string filename)
		{
			System.Diagnostics.ProcessStartInfo psi = new System.Diagnostics.ProcessStartInfo(Properties.Settings.Default.JavaPath);
			psi.WorkingDirectory = Properties.Settings.Default.JavaCameraSummaryXMLPath;
			string filepath = "\"" + Directory.GetCurrentDirectory() + "\\" + filename + "\"";
			psi.Arguments = Properties.Settings.Default.JavaCameraSummaryXMLArguments + " " + filepath;
			psi.RedirectStandardOutput = true;
			psi.WindowStyle = System.Diagnostics.ProcessWindowStyle.Hidden;
			psi.UseShellExecute = false;
			System.Diagnostics.Process listFiles = System.Diagnostics.Process.Start(psi);
			System.IO.StreamReader myOutput = listFiles.StandardOutput;
			listFiles.WaitForExit();
			// searches for a newly created file
			string output = Properties.Settings.Default.JavaCameraSummaryXMLPath + "\\" + 
				Utils.IO.GetLatestCreatedFile(Properties.Settings.Default.JavaCameraSummaryXMLPath);
			return output;
		}

		// This delegate enables asynchronous calls for setting the text property on a TextBox control.
		delegate void SetTextCallback(int nrProcessedMsgs, string msgText, string correlationId);

		private void SetLabels1(int nrProcessedMsgs, string msgText, string correlationId)
		{
			labelNrProcessedMsgs1.Text = nrProcessedMsgs.ToString();
			labelLastMsg1.Text = msgText;
			labelCorrelationId1.Text = correlationId;
		}

	}
}
