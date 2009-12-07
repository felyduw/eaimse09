using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;

namespace IntegrationWrapper_CameraListBeautifier
{
	public partial class FormMainIWCameraListBeautifier : Form
	{
		MessageQueue iwCameraListBeautifierInboxQueue = null;
		MessageQueue iwCameraListBeautifierOutboxQueue = null;

		public FormMainIWCameraListBeautifier()
		{
			InitializeComponent();
			InitializeMessageQueues();
		}

		private void InitializeMessageQueues()
		{
			iwCameraListBeautifierInboxQueue = MessageQueues.CreateOrUseQueue(@".\Private$\EAICameraListBeautifierInbox");
			iwCameraListBeautifierOutboxQueue = MessageQueues.CreateOrUseQueue(@".\Private$\EAICameraListBeautifierOutbox");
		}
	}
}
