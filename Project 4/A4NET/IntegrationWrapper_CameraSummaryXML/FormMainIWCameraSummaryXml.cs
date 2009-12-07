using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;

namespace IntegrationWrapper_CameraSummaryXML
{
	public partial class FormMainIWCameraSummaryXml : Form
	{
		MessageQueue iwCameraSummaryXmlInboxQueue = null;
		MessageQueue iwCameraSummaryXmlOutboxQueue = null;

		public FormMainIWCameraSummaryXml()
		{
			InitializeComponent();
			InitializeMessageQueues();
		}

		private void InitializeMessageQueues()
		{
			iwCameraSummaryXmlInboxQueue = MessageQueues.CreateOrUseQueue(@".\Private$\EAICameraSummaryXMLInbox");
			iwCameraSummaryXmlOutboxQueue = MessageQueues.CreateOrUseQueue(@".\Private$\EAICameraSummaryXMLOutbox");
		}
	}
}
