using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;
using System.Threading;
using System.Xml;
using System.Text.RegularExpressions;

namespace IntegrationWrapper_CameraSearchXML
{
	public partial class FormMainIWCameraSearchXml : Form
	{
		MessageQueue iwCameraSearchXmlInboxQueue = null;
		MessageQueue iwCameraSearchXmlOutboxQueue = null;

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
			iwCameraSearchXmlInboxQueue = MessageQueues.CreateOrUseQueue(@".\Private$\EAICameraSearchXMLInbox");
			iwCameraSearchXmlInboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(string) }));
			iwCameraSearchXmlOutboxQueue = MessageQueues.CreateOrUseQueue(@".\Private$\EAICameraSearchXMLOutbox");
		}

		private void InitializeThreads()
		{
			ThreadStart job1 = new ThreadStart(DoWork);
			Thread thread1 = new Thread(job1);
			thread1.Start();
		}

		/// <summary>
		/// To manage communication from the UserInterface application to the 
		/// CameraSearchXML application
		/// </summary>
		private void DoWork()
		{
			int nrProcessedMsgs = 0;
			while (true)
			{
				// receives message
				System.Messaging.Message incomingMsg = iwCameraSearchXmlInboxQueue.Receive();
				string msg = (string)incomingMsg.Body;
				// performs query by calling the original CameraSearchXML java application
				string callJavaCameraSearchXMLResult = CallJavaCameraSearchXML(msg);
				// parse result to obtain the filename
				string filename = GetFilename(callJavaCameraSearchXMLResult);
				// open the file
				XmlDocument xmlDoc = new XmlDocument();
				xmlDoc.LoadXml(Properties.Settings.Default.JavaCameraSearchXMLPath + "\\" + filename);
				// send the result XML to the outbox message queue
				iwCameraSearchXmlOutboxQueue.Send(filename);
				// write to the form
				if (labelNrProcessedMsgs1.InvokeRequired && labelLastMsg1.InvokeRequired)
				{
					SetTextCallback d = new SetTextCallback(SetLabels1);
					this.Invoke(d, new object[] { ++nrProcessedMsgs, msg });
				}
			}
		}

		private string CallJavaCameraSearchXML(string query)
		{
			System.Diagnostics.ProcessStartInfo psi = new System.Diagnostics.ProcessStartInfo(Properties.Settings.Default.JavaPath);
			psi.WorkingDirectory = Properties.Settings.Default.JavaCameraSearchXMLPath;
			psi.Arguments = Properties.Settings.Default.JavaCameraSearchXMLArguments + " " + query;
			psi.RedirectStandardOutput = true;
			psi.WindowStyle = System.Diagnostics.ProcessWindowStyle.Normal;
			psi.UseShellExecute = false;
			System.Diagnostics.Process listFiles = System.Diagnostics.Process.Start(psi);
			System.IO.StreamReader myOutput = listFiles.StandardOutput;
			listFiles.WaitForExit();
			string output = myOutput.ReadToEnd();
			return output;
		}

		private string GetFilename(string inputString)
		{
			Match m;
			string pattern = "File written: (.+)";
			m = Regex.Match(inputString, pattern, RegexOptions.IgnoreCase | RegexOptions.Compiled);
			if (m.Success)
			{
				return m.Groups[1].Value;
			}
			return null;
		}

		// This delegate enables asynchronous calls for setting the text property on a TextBox control.
		delegate void SetTextCallback(int nrProcessedMsgs, string msgText);

		private void SetLabels1(int nrProcessedMsgs, string msgText)
		{
			labelNrProcessedMsgs1.Text = nrProcessedMsgs.ToString();
			labelLastMsg1.Text = msgText;
		}


	}
}
