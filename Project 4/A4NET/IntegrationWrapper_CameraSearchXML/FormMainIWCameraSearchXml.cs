using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;
using System.Threading;
using System.Xml;
using System.Text.RegularExpressions;
using System.Text;
using System.IO;

namespace EAI.A4.IntegrationWrapper_CameraSearchXML
{
	public partial class FormMainIWCameraSearchXml : Form
	{
		MessageQueue iwCameraSearchXmlInboxQueue = null;

		public FormMainIWCameraSearchXml()
		{
			InitializeComponent();
			InitializeMessageQueues();
			InitializeThreads();
		}

		/// <summary>
		/// Initializes the two message queues.
		/// </summary>
		private void InitializeMessageQueues()
		{
			iwCameraSearchXmlInboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraSearchXmlInboxQueue);
			iwCameraSearchXmlInboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(string) }));
			iwCameraSearchXmlInboxQueue.MessageReadPropertyFilter.SetAll();
		}

		private void InitializeThreads()
		{
			ThreadStart jobReceiveMessages = new ThreadStart(ReceiveMessages);
			Thread threadReceiveMessages = new Thread(jobReceiveMessages);
			threadReceiveMessages.Start();
		}

		/// <summary>
		/// To manage communication from the UserInterface application to the 
		/// CameraSearchXML application
		/// </summary>
		private void ReceiveMessages()
		{
			int nrProcessedMsgs = 0;
			while (true)
			{
				// receives message
				System.Messaging.Message incomingMsg = iwCameraSearchXmlInboxQueue.Receive();
				string msg = (string)incomingMsg.Body;
				PerformAndDispatch(msg, incomingMsg.CorrelationId);
				// write to the form
				if (labelNrProcessedMsgs1.InvokeRequired && labelLastMsg1.InvokeRequired)
				{
					SetTextCallback d = new SetTextCallback(SetLabels1);
					this.Invoke(d, new object[] { ++nrProcessedMsgs, msg, incomingMsg.CorrelationId });
				}
			}
		}

		/// <summary>
		/// Creates a new thread to call the CameraSearchXML application and to send the result to the outbox message queue
		/// </summary>
		/// <param name="msg"></param>
		private void PerformAndDispatch(string msg, string correlationId)
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
		public string Msg;
		public string CorrelationId;

		private MessageQueue iwCameraSearchXmlOutboxQueue = null;

		public Work()
		{
			iwCameraSearchXmlOutboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraSearchXmlOutboxQueue);
			iwCameraSearchXmlOutboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(XmlDocument) }));
		}

		public void DoWork()
		{
			try
			{
				// performs query by calling the original CameraSearchXML java application
				string filepath = CallJavaCameraSearchXML(Msg);
				// opens and reads the file
				XmlDocument xmlDoc = new XmlDocument();
				string result = File.ReadAllText(filepath, Encoding.Default);
				xmlDoc.LoadXml(result);
				// send the result XML to the outbox message queue
				System.Messaging.Message outcomingMsg = new System.Messaging.Message(xmlDoc);
				outcomingMsg.CorrelationId = CorrelationId;
				iwCameraSearchXmlOutboxQueue.Send(outcomingMsg);
			}
			catch (System.Exception exc)
			{
				// log
			}
		}

		private string CallJavaCameraSearchXML(string query)
		{
			System.Diagnostics.ProcessStartInfo psi = new System.Diagnostics.ProcessStartInfo(Properties.Settings.Default.JavaPath);
			psi.WorkingDirectory = Properties.Settings.Default.JavaCameraSearchXMLPath;
			psi.Arguments = Properties.Settings.Default.JavaCameraSearchXMLArguments + " " + query;
			psi.RedirectStandardOutput = true;
			psi.WindowStyle = System.Diagnostics.ProcessWindowStyle.Hidden;
			psi.UseShellExecute = false;
			System.Diagnostics.Process listFiles = System.Diagnostics.Process.Start(psi);
			System.IO.StreamReader myOutput = listFiles.StandardOutput;
			listFiles.WaitForExit();
			string output = Properties.Settings.Default.JavaCameraSearchXMLPath + "\\" +
				Utils.IO.GetLatestCreatedFile(Properties.Settings.Default.JavaCameraSearchXMLPath);
			return output;
		}

	}

}
