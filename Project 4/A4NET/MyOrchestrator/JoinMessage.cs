using System.Xml;

namespace EAI.A4.MyOrchestrator
{
	class JoinMessage
	{
		public XmlDocument MsgCameraSummaryXml { get; set; }
		
		public string MsgCameraListBeautifier { get; set; }

		public JoinMessage()
		{
			MsgCameraSummaryXml = null;
			MsgCameraListBeautifier = null;
		}

		public bool IsComplete()
		{
			return (MsgCameraSummaryXml != null && MsgCameraListBeautifier != null);
		}
	}
}
