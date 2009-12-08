using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;
using System.Threading;
using System.Xml;
using System;
using System.IO;
using System.Text;

namespace EAI.A4.IntegrationWrapper_CameraSummaryXML
{
	public partial class FormMainIWCameraSummaryXml : Form
	{
		MessageQueue iwCameraSummaryXmlInboxQueue = null;

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
		}

		private void InitializeThreads()
		{
			ThreadStart jobReceiveMessages = new ThreadStart(ReceiveMessages);
			Thread threadReceiveMessages = new Thread(jobReceiveMessages);
			threadReceiveMessages.Start();
		}

		private void ReceiveMessages()
		{
			int nrProcessedMsgs = 0;
			while (true)
			{
				// receives message
				System.Messaging.Message incomingMsg = iwCameraSummaryXmlInboxQueue.Receive();
				//incomingMsg.CorrelationId = incomingMsg.Id;
				XmlDocument msg = (XmlDocument)incomingMsg.Body;
				PerformAndDispatch(msg, incomingMsg.CorrelationId);
				// write to the form
				if (labelNrProcessedMsgs1.InvokeRequired && labelLastMsg1.InvokeRequired)
				{
					SetTextCallback d = new SetTextCallback(SetLabels1);
					this.Invoke(d, new object[] { ++nrProcessedMsgs, msg.InnerXml.Substring(0, 100), incomingMsg.CorrelationId });
				}
			}
		}

		/// <summary>
		/// Creates a new thread to call the CameraSummaryXML application and to send the result to the outbox message queue
		/// </summary>
		/// <param name="msg"></param>
		private void PerformAndDispatch(XmlDocument msg, string correlationId)
		{
			Work w = new Work();
			w.Msg = msg;
			w.CorrelationId = correlationId;
			ThreadStart threadDelegate = new ThreadStart(w.DoWork);
			Thread newThread = new Thread(threadDelegate);
			newThread.Start();
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


	class Work
	{
		public XmlDocument Msg;
		public string CorrelationId;

		MessageQueue iwCameraSummaryXmlOutboxQueue = null;

		public Work()
		{
			iwCameraSummaryXmlOutboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraSummaryXmlOutboxQueue);
			iwCameraSummaryXmlOutboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(XmlDocument) }));
		}

		public void DoWork()
		{
			try
			{
				// writes the msg to a new XML file
				string filename = "IWCameraSummary" + DateTime.Now.ToString("yyyyMMddHHmmssffff") + ".xml";
				XmlTextWriter writer = new XmlTextWriter(filename, new System.Text.UTF8Encoding());
				writer.Formatting = Formatting.Indented;
				Msg.WriteTo(writer);
				writer.Close();
				// summarizes the information and produces a new XML file by calling the original CameraSummaryXML java application
				string filepath = CallJavaCameraSummaryXML(filename);
				if (filepath != null)
				{
					// reads the XML from the newly created file
					XmlDocument xmlDoc = new XmlDocument();
					string result = File.ReadAllText(filepath, Encoding.Default);
					xmlDoc.LoadXml(result);
					// send the result XML to the outbox message queue
					System.Messaging.Message outcomingMsg = new System.Messaging.Message(xmlDoc);
					outcomingMsg.CorrelationId = CorrelationId;
					iwCameraSummaryXmlOutboxQueue.Send(outcomingMsg);
				}

			}
			catch (Exception exc)
			{
				// log
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
			string latestFile = Utils.IO.GetLatestCreatedFile(Properties.Settings.Default.JavaCameraSummaryXMLPath);
			if (latestFile.StartsWith("Summary_"))
			{
				return Properties.Settings.Default.JavaCameraSummaryXMLPath + "\\" + latestFile;
			}
			return null;
		}

	}
}
