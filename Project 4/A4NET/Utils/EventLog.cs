
namespace EAI.A4.Utils
{
	public class EventLog
	{
		public static void Write2EventLog(string msg)
		{
			if (!System.Diagnostics.EventLog.SourceExists("EAIA4NET"))
			{
				System.Diagnostics.EventLog.CreateEventSource("EAIA4NET", "Application");
			}
			System.Diagnostics.EventLog EventLog1 = new System.Diagnostics.EventLog();
			EventLog1.Source = "EAIA4NET";
			EventLog1.WriteEntry(msg);
		}

	}
}
