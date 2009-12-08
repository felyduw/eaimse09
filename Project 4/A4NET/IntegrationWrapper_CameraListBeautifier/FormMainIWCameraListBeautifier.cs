using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;
using System.Threading;
using System.Xml;
using System;
using System.IO;
using System.Text;

namespace EAI.A4.IntegrationWrapper_CameraListBeautifier
{
	public partial class FormMainIWCameraListBeautifier : Form
	{
		MessageQueue iwCameraListBeautifierInboxQueue = null;

		public FormMainIWCameraListBeautifier()
		{
			InitializeComponent();
			InitializeMessageQueues();
			InitializeThreads();
		}

		private void InitializeMessageQueues()
		{
			iwCameraListBeautifierInboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraListBeautifierInboxQueue);
			iwCameraListBeautifierInboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(XmlDocument) }));
			iwCameraListBeautifierInboxQueue.MessageReadPropertyFilter.SetAll();
		}

		private void InitializeThreads()
		{
			ThreadStart jobReceiveMessages = new ThreadStart(ReceiveMessages);
			Thread threadReceiveMessages = new Thread(jobReceiveMessages);
			threadReceiveMessages.Start();
		}

		/// <summary>
		/// Reads the messages in the inbox message queue and creates a new thread to perform the work.
		/// </summary>
		private void ReceiveMessages()
		{
			int nrProcessedMsgs = 0;
			while (true)
			{
				// receives message
				System.Messaging.Message incomingMsg = iwCameraListBeautifierInboxQueue.Receive();
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
		/// Creates a new thread to call the CameraListBeautifier application and to send the result to the outbox message queue
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

		MessageQueue iwCameraListBeautifierOutboxQueue = null;

		public Work()
		{
			iwCameraListBeautifierOutboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraListBeautifierOutboxQueue);
			iwCameraListBeautifierOutboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(string) }));
		}

		public void DoWork()
		{
			// writes the msg to a new XML file
			string filename = "IWCameraListBeautifier" + DateTime.Now.ToString("yyyyMMddHHmmssffff") + ".xml";
			XmlTextWriter writer = new XmlTextWriter(filename, new System.Text.UTF8Encoding());
			writer.Formatting = Formatting.Indented;
			Msg.WriteTo(writer);
			writer.Close();
			// summarizes the information and produces a new XML file by calling the original CameraListBeautifier java application
			string filepath = CallJavaCameraListBeautifier(filename);
			// reads the HTML from the newly created file
			string result = File.ReadAllText(filepath, Encoding.Default);
			// send the result HTML to the outbox message queue
			System.Messaging.Message outcomingMsg = new System.Messaging.Message(result);
			outcomingMsg.CorrelationId = CorrelationId;
			iwCameraListBeautifierOutboxQueue.Send(outcomingMsg);
		}

		private string CallJavaCameraListBeautifier(string xmlFilename)
		{
			System.Diagnostics.ProcessStartInfo psi = new System.Diagnostics.ProcessStartInfo(Properties.Settings.Default.MsxslPath);
			psi.WorkingDirectory = Properties.Settings.Default.JavaCameraListBeautifierPath;
			string xmlFilepath = "\"" + Directory.GetCurrentDirectory() + "\\" + xmlFilename + "\"";
			string htmlFilename = "CameraListBeautifier" + DateTime.Now.ToString("yyyyMMddHHmmssffff") + ".html";
			psi.Arguments = xmlFilepath + " CameraListBeautifier.xslt -o " + htmlFilename;
			psi.RedirectStandardOutput = true;
			psi.WindowStyle = System.Diagnostics.ProcessWindowStyle.Hidden;
			psi.UseShellExecute = false;
			System.Diagnostics.Process listFiles = System.Diagnostics.Process.Start(psi);
			System.IO.StreamReader myOutput = listFiles.StandardOutput;
			listFiles.WaitForExit();
			// searches for a newly created file
			string output = Properties.Settings.Default.JavaCameraListBeautifierPath + "\\" + htmlFilename;
			return output;
		}

	}

}
