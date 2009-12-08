using System;
using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;
using System.Threading;
using System.IO;
using EAI.Common;

namespace EAI.A4.UserInterface
{
	public partial class FormMain : Form
	{
		MessageQueue userInterfaceInboxQueue = null;
		MessageQueue userInterfaceOutboxQueue = null;

		public FormMain()
		{
			InitializeComponent();
			InitializeMessageQueues();
			InitializeThreads();
		}

		private void InitializeMessageQueues()
		{
			userInterfaceInboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.userInterfaceInboxQueue);
			userInterfaceInboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(A4TupleMessage) }));
			userInterfaceInboxQueue.MessageReadPropertyFilter.SetAll();
			userInterfaceOutboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.userInterfaceOutboxQueue);
			userInterfaceOutboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(string) }));
		}

		private void InitializeThreads()
		{
			ThreadStart jobReceiveMessages = new ThreadStart(ReceiveMessages);
			Thread threadReceiveMessages = new Thread(jobReceiveMessages);
			threadReceiveMessages.Start();
		}

		private void ReceiveMessages()
		{
			while (true)
			{
				// receives message
				System.Messaging.Message incomingMsg = userInterfaceInboxQueue.Receive();
				A4TupleMessage msg = (A4TupleMessage)incomingMsg.Body;
				// write to the file
				string filenameXml = "CameraSummaryXml-" + incomingMsg.CorrelationId.Replace(@"\", "_") + ".xml";
				string filenameHtml = "CameraListBeautifier-" + incomingMsg.CorrelationId.Replace(@"\", "_") + ".html";
				File.WriteAllText(filenameXml, msg.CameraSummaryXmlResult.InnerXml);
				File.WriteAllText(filenameHtml, msg.CameraListBeautifierResult);
				// open dialog to inform
				SetTextCallback d = new SetTextCallback(CallMessageBox);
				this.Invoke(d, new object[] { incomingMsg.CorrelationId, filenameXml, filenameHtml });
			}
		}

		delegate void SetTextCallback(string correlationId, string filenameXml, string filenameHtml);

		private void CallMessageBox(string correlationId, string filenameXml, string filenameHtml)
		{
			textBoxOutput.Text += "new files from incoming msg " + correlationId + ": \r\n";
			textBoxOutput.Text += "from CameraSummaryXML: " + filenameXml + "\r\n";
			textBoxOutput.Text += "from CameraListBeautifier: " + filenameHtml + "\r\n\r\n";
			toolStripStatusLabelResults.Text = String.Empty;
		}

		#region Handlers

		private void buttonGo_Click(object sender, EventArgs e)
		{
			toolStripStatusLabelResults.Visible = true;
			toolStripStatusLabelResults.Text = "processing \"" + textBoxQuery.Text + "\"...";
			userInterfaceOutboxQueue.Send(textBoxQuery.Text);
			textBoxQuery.Text = String.Empty;
		}

		private void buttonClean_Click(object sender, EventArgs e)
		{
			textBoxOutput.Text = string.Empty;
		}

		#endregion Handlers

	}
}
