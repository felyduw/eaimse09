using System;
using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;

namespace EAI.A4.UserInterface
{
	public partial class FormMain : Form
	{
		MessageQueue inboxQueue = null;
		MessageQueue outboxQueue = null;

		public FormMain()
		{
			InitializeComponent();
			InitializeMessageQueues();
		}

		private void InitializeMessageQueues()
		{
			inboxQueue = MessageQueues.CreateOrUseQueue(@".\Private$\EAIUserInterfaceInbox");
			outboxQueue = MessageQueues.CreateOrUseQueue(@".\Private$\EAIUserInterfaceOutbox");
			outboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(string) }));
		}

		#region Handlers

		private void buttonGo_Click(object sender, EventArgs e)
		{
			toolStripStatusLabelResults.Visible = true;
			toolStripStatusLabelResults.Text = "processing \"" + textBoxQuery.Text + "\"...";
			toolStripProgressBarWork.Visible = true;
			outboxQueue.Send(textBoxQuery.Text);
			textBoxQuery.Text = String.Empty;
		}

		#endregion Handlers

	}
}
