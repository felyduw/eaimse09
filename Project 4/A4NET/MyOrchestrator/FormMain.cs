using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;

namespace EAI.A4.MyOrchestrator
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
			userInterfaceInboxQueue = MessageQueues.CreateOrUseQueue(@".\Private$\EAIUserInterfaceInbox");
			userInterfaceOutboxQueue = MessageQueues.CreateOrUseQueue(@".\Private$\EAIUserInterfaceOutbox");
		}


	}
}
