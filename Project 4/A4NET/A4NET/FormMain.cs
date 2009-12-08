using System;
using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;
using System.Threading;
using System.IO;

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
			userInterfaceInboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(string) }));
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
				string msg = (string)incomingMsg.Body;
				// write to the file
				string filename = "Final-" + incomingMsg.CorrelationId.Replace(@"\", "_") + ".html";
				File.WriteAllText(filename, msg);
				// open dialog to inform
				SetTextCallback d = new SetTextCallback(CallMessageBox);
				this.Invoke(d, new object[] { filename });
			}
		}

		delegate void SetTextCallback(string filename);

		private void CallMessageBox(string filename)
		{
			MessageBox.Show(this, "new file: " + filename, "EAI A4 - UserInterface", MessageBoxButtons.OK, MessageBoxIcon.Information);
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

		#endregion Handlers

	}
}
