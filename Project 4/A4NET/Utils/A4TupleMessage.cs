using System.Xml;

namespace EAI.Common
{
	public class A4TupleMessage
	{
		public XmlDocument CameraSummaryXmlResult { get; set; }

		public string CameraListBeautifierResult { get; set; }

		public A4TupleMessage()
		{
			CameraSummaryXmlResult = null;
			CameraListBeautifierResult = null;
		}

		public A4TupleMessage(XmlDocument xmlDoc, string htmlDoc)
		{
			CameraSummaryXmlResult = xmlDoc;
			CameraListBeautifierResult = htmlDoc;
		}
	}
}
