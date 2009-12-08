using System.Messaging;
using System.Threading;
using System.Windows.Forms;
using EAI.A4.Utils;
using System.Xml;
using System.Collections.Generic;
using EAI.Common;

namespace EAI.A4.MyOrchestrator
{
	public partial class FormMainMyOrchestrator : Form
	{
		Dictionary<string, JoinMessage> joinMessages = new Dictionary<string, JoinMessage>();

		#region Message Queues declaration
		MessageQueue userInterfaceInboxQueue = null;
		MessageQueue userInterfaceOutboxQueue = null;
		MessageQueue iwCameraSearchXmlInboxQueue = null;
		MessageQueue iwCameraSearchXmlOutboxQueue = null;
		MessageQueue iwCameraSummaryXmlInboxQueue = null;
		MessageQueue iwCameraSummaryXmlOutboxQueue = null;
		MessageQueue iwCameraListBeautifierInboxQueue = null;
		MessageQueue iwCameraListBeautifierOutboxQueue = null;
		#endregion Message Queues declaration

		public FormMainMyOrchestrator()
		{
			InitializeComponent();
			InitializeMessageQueues();
			InitializeThreads();
		}

		/// <summary>
		/// Initialize all the necessary message queues.
		/// </summary>
		private void InitializeMessageQueues()
		{
			// User Interface
			userInterfaceInboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.EAIUserInterfaceInbox);
			userInterfaceInboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(A4TupleMessage) }));
			userInterfaceInboxQueue.MessageReadPropertyFilter.SetAll();
			//userInterfaceInboxQueue.
			userInterfaceOutboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.EAIUserInterfaceOutbox);
			userInterfaceOutboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(string) }));
			userInterfaceOutboxQueue.MessageReadPropertyFilter.SetAll();
			// Camera Search XML
			iwCameraSearchXmlInboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraSearchXmlInboxQueue);
			iwCameraSearchXmlInboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(string) }));
			iwCameraSearchXmlInboxQueue.MessageReadPropertyFilter.SetAll();
			iwCameraSearchXmlOutboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraSearchXmlOutboxQueue);
			iwCameraSearchXmlOutboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(XmlDocument) }));
			iwCameraSearchXmlOutboxQueue.MessageReadPropertyFilter.SetAll();
			// Camera Summary XML
			iwCameraSummaryXmlInboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraSummaryXmlInboxQueue);
			iwCameraSummaryXmlInboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(XmlDocument) }));
			iwCameraSummaryXmlInboxQueue.MessageReadPropertyFilter.SetAll();
			iwCameraSummaryXmlOutboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraSummaryXmlOutboxQueue);
			iwCameraSummaryXmlOutboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(XmlDocument) }));
			iwCameraSummaryXmlOutboxQueue.MessageReadPropertyFilter.SetAll();
			// Camera List Beautifier
			iwCameraListBeautifierInboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraListBeautifierInboxQueue);
			iwCameraListBeautifierInboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(XmlDocument) }));
			iwCameraListBeautifierInboxQueue.MessageReadPropertyFilter.SetAll();
			iwCameraListBeautifierOutboxQueue = MessageQueues.CreateOrUseQueue(Properties.Settings.Default.iwCameraListBeautifierOutboxQueue);
			iwCameraListBeautifierOutboxQueue.Formatter = new XmlMessageFormatter((new System.Type[] { typeof(string) }));
			iwCameraListBeautifierOutboxQueue.MessageReadPropertyFilter.SetAll();
		}

		/// <summary>
		/// Initialize and start the three necessary threads.
		/// </summary>
		private void InitializeThreads()
		{
			// To manage communication from the UserInterface application to the 
			// CameraSearchXML application
			ThreadStart job1 = new ThreadStart(ThreadJob1);
			Thread thread1 = new Thread(job1);
			thread1.Start();
			// To manage communication from the CameraSearchXML application to the 
			// CameraSummaryXML application and the CameraBeautifier application
			ThreadStart job2 = new ThreadStart(ThreadJob2);
			Thread thread2 = new Thread(job2);
			thread2.Start();
			// To manage communication from the CameraSummaryXML application to the UserInterface appl
			ThreadStart job3 = new ThreadStart(ThreadJob3);
			Thread thread3 = new Thread(job3);
			thread3.Start();
			// To manage communication from the CameraBeautifier application to the UserInterface appl
			ThreadStart job4 = new ThreadStart(ThreadJob4);
			Thread thread4 = new Thread(job4);
			thread4.Start();
		}

		// This delegate enables asynchronous calls for setting the text property on a TextBox control.
		delegate void SetTextCallback(int nrProcessedMsgs, string msgText, string correlationId);


		/// <summary>
		/// To manage communication from the UserInterface application to the 
		/// CameraSearchXML application
		/// </summary>
		private void ThreadJob1()
		{
			int nrProcessedMsgs = 0;
			while (true)
			{
				System.Messaging.Message incomingMsg = userInterfaceOutboxQueue.Receive();
				incomingMsg.CorrelationId = incomingMsg.Id;
				iwCameraSearchXmlInboxQueue.Send(incomingMsg);
				if (labelNrProcessedMsgs1.InvokeRequired && labelLastMsg1.InvokeRequired)
				{
					SetTextCallback d = new SetTextCallback(SetLabels1);
					string msg = (string)incomingMsg.Body;
					this.Invoke(d, new object[] { ++nrProcessedMsgs, msg, incomingMsg.CorrelationId });
				}
			}
		}

		private void SetLabels1(int nrProcessedMsgs, string msgText, string correlationId)
		{
			labelNrProcessedMsgs1.Text = nrProcessedMsgs.ToString();
			labelLastMsg1.Text = msgText;
			labelCorrelationId1.Text = correlationId;
		}

		// To manage communication from the CameraSearchXML application to the 
		// CameraSummaryXML application and the CameraBeautifier application
		private void ThreadJob2()
		{
			int nrProcessedMsgs = 0;
			while (true)
			{
				System.Messaging.Message incomingMsg = iwCameraSearchXmlOutboxQueue.Receive();
				iwCameraSummaryXmlInboxQueue.Send(incomingMsg);
				iwCameraListBeautifierInboxQueue.Send(incomingMsg);
				if (labelNrProcessedMsgs2.InvokeRequired && labelLastMsg2.InvokeRequired)
				{
					SetTextCallback d = new SetTextCallback(SetLabels2);
					string msg = ((XmlDocument)incomingMsg.Body).InnerXml.Substring(0, 100);
					this.Invoke(d, new object[] { ++nrProcessedMsgs, msg, incomingMsg.CorrelationId });
				}
			}
		}

		private void SetLabels2(int nrProcessedMsgs, string msgText, string correlationId)
		{
			labelNrProcessedMsgs2.Text = nrProcessedMsgs.ToString();
			labelLastMsg2.Text = msgText;
			labelCorrelationId2.Text = correlationId;
		}

		// To manage communication from the CameraSummaryXML application to the UserInterface appl
		private void ThreadJob3()
		{
			int nrProcessedMsgs = 0;
			while (true)
			{
				System.Messaging.Message incomingMsg = iwCameraSummaryXmlOutboxQueue.Receive();
				lock (joinMessages)
				{
					if (joinMessages.ContainsKey(incomingMsg.CorrelationId))
					{
						// join the two messages
						A4TupleMessage joinedMsgs = new A4TupleMessage((XmlDocument)incomingMsg.Body,
							joinMessages[incomingMsg.CorrelationId].MsgCameraListBeautifier);
						/*
						string joinedMsgs = joinMessages[incomingMsg.CorrelationId].MsgCameraListBeautifier +
							"<br/><br/><textarea cols=\"80\" rows=\"10\">" + ((XmlDocument)incomingMsg.Body).InnerXml + "</textarea>";
						*/
						System.Messaging.Message finalMsg = new System.Messaging.Message(joinedMsgs);
						finalMsg.CorrelationId = incomingMsg.CorrelationId;
						userInterfaceInboxQueue.Send(finalMsg);
						joinMessages.Remove(incomingMsg.CorrelationId);
					}
					else
					{
						JoinMessage newJoinMsg = new JoinMessage();
						newJoinMsg.MsgCameraSummaryXml = (XmlDocument)incomingMsg.Body;
						joinMessages.Add(incomingMsg.CorrelationId, newJoinMsg);
					}
				}
				if (labelNrProcessedMsgs3.InvokeRequired && labelLastMsg3.InvokeRequired)
				{
					SetTextCallback d = new SetTextCallback(SetLabels3);
					string msg = ((XmlDocument)incomingMsg.Body).InnerXml.Substring(0, 100);
					this.Invoke(d, new object[] { ++nrProcessedMsgs, msg, incomingMsg.CorrelationId });
				}
			}
		}

		private void SetLabels3(int nrProcessedMsgs, string msgText, string correlationId)
		{
			labelNrProcessedMsgs3.Text = nrProcessedMsgs.ToString();
			labelLastMsg3.Text = msgText;
			labelCorrelationId3.Text = correlationId;
		}

		// To manage communication from the CameraListBeautifier application to the UserInterface appl
		private void ThreadJob4()
		{
			int nrProcessedMsgs = 0;
			while (true)
			{
				System.Messaging.Message incomingMsg = iwCameraListBeautifierOutboxQueue.Receive();
				lock (joinMessages)
				{
					if (joinMessages.ContainsKey(incomingMsg.CorrelationId))
					{
						// join the two messages
						A4TupleMessage joinedMsgs = new A4TupleMessage(joinMessages[incomingMsg.CorrelationId].MsgCameraSummaryXml,
							(string)incomingMsg.Body);
						/*
						string joinedMsgs = (string)incomingMsg.Body + "<br/><br/><textarea cols=\"80\" rows=\"10\">" +
							joinMessages[incomingMsg.CorrelationId].MsgCameraSummaryXml.InnerXml + "</textarea>";
						*/
						System.Messaging.Message finalMsg = new System.Messaging.Message(joinedMsgs);
						finalMsg.CorrelationId = incomingMsg.CorrelationId;
						userInterfaceInboxQueue.Send(finalMsg);
						joinMessages.Remove(incomingMsg.CorrelationId);
					}
					else
					{
						JoinMessage newJoinMsg = new JoinMessage();
						newJoinMsg.MsgCameraListBeautifier = (string)incomingMsg.Body;
						joinMessages.Add(incomingMsg.CorrelationId, newJoinMsg);
					}
				}
				if (labelNrProcessedMsgs4.InvokeRequired && labelLastMsg4.InvokeRequired)
				{
					SetTextCallback d = new SetTextCallback(SetLabels4);
					string msg = ((string)incomingMsg.Body).Substring(0, 100);
					this.Invoke(d, new object[] { ++nrProcessedMsgs, msg, incomingMsg.CorrelationId });
				}
				
			}
		}

		private void SetLabels4(int nrProcessedMsgs, string msgText, string correlationId)
		{
			labelLastMsg4.Text = msgText;
			labelCorrelationId4.Text = correlationId;
		}



	}
}
