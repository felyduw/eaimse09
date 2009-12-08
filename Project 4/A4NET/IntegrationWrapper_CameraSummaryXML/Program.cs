using System;
using System.Windows.Forms;
using EAI.A4.Utils;

namespace EAI.A4.IntegrationWrapper_CameraSummaryXML
{
	static class Program
	{
		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main()
		{
			try
			{
				Application.EnableVisualStyles();
				Application.SetCompatibleTextRenderingDefault(false);
				Application.Run(new FormMainIWCameraSummaryXml());

			}
			catch (Exception exc)
			{
				EventLog.Write2EventLog(exc.Message);
				throw new Exception("EAI A4 - IntegrationWrapper_CameraSummaryXML", exc);
			}
		}
	}
}
