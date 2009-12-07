using System;
using System.Windows.Forms;
using EAI.A4.Utils;

namespace IntegrationWrapper_CameraListBeautifier
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
				Application.Run(new FormMainIWCameraListBeautifier());

			}
			catch (Exception exc)
			{
				EventLog.Write2EventLog(exc.Message);
				throw new Exception("EAI A4 - IntegrationWrapper_CameraListBeautifier", exc);
			}
		}
	}
}
