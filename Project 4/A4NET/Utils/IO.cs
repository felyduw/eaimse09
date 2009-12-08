using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace EAI.A4.Utils
{
	public class IO
	{
		public static string GetLatestCreatedFile(string directory)
		{
			DirectoryInfo di = new DirectoryInfo(directory);
			FileInfo[] fi = di.GetFiles();
			string latestCreatedFileName = null;
			DateTime latestCreatedFileDate = DateTime.MinValue;
			foreach (FileInfo fiTemp in fi)
			{
				if (fiTemp.LastWriteTime > latestCreatedFileDate)
				{
					latestCreatedFileDate = fiTemp.LastWriteTime;
					latestCreatedFileName = fiTemp.Name;
				}
			}
			return latestCreatedFileName;
		}
	}
}
