using System.Messaging;

namespace EAI.A4.Utils
{
	public class MessageQueues
	{
		public static MessageQueue CreateOrUseQueue(string queueName)
		{
			MessageQueue newMessageQueue = null;
			if (!MessageQueue.Exists(queueName))
			{
				newMessageQueue = MessageQueue.Create(queueName);
				newMessageQueue.SetPermissions("Everyone",
					MessageQueueAccessRights.GenericRead | MessageQueueAccessRights.GenericWrite,
					AccessControlEntryType.Set);
			}
			else
			{
				newMessageQueue = new MessageQueue(queueName);
			}
			return newMessageQueue;
		}

	}
}
