using System.Messaging;
using System.Windows.Forms;
using EAI.A4.Utils;
using System.Threading;
using System.Xml;

namespace EAI.A4.IntegrationWrapper_CameraListBeautifier
{
	public partial class FormMainIWCameraListBeautifier : Form
	{
		MessageQueue iwCameraListBeautifierInboxQueue = null;
		MessageQueue iwCameraListBeautifierOutboxQueue = null;

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
			iwCameraListBeautifierOutboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraListBeautifierOutboxQueue);
		}

		private void InitializeThreads()
		{
			ThreadStart job1 = new ThreadStart(DoWork);
			Thread thread1 = new Thread(job1);
			thread1.Start();
		}

		private void DoWork()
		{
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
}
