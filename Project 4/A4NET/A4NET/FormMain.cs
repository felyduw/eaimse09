using System;
using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;

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
		}

		private void InitializeMessageQueues()
		{
			userInterfaceInboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.userInterfaceInboxQueue);
			userInterfaceInboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(string) }));
			userInterfaceOutboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.userInterfaceOutboxQueue);
			userInterfaceOutboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(string) }));
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
