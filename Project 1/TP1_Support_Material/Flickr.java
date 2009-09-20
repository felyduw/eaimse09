
/*
 * Small application showing how to query Flickr
 *
 * (c) Paulo Marques (pmarques@dei.uc.pt), DEI/FCTUC
 * v1.0 Sep/2008
 */

import java.net.*;
import java.io.*;

public class Flickr
{
  public static void main(String[] args)
    throws IOException
  {
    // Query String
    String query = "Nature";

    // Query Flcikr, allocating a BufferedReader for the resultign page
    URL flickUrl = new URL("http://www.flickr.com/search/?q=" + 
		URLEncoder.encode(query, "UTF-8"));
    URLConnection connection = flickUrl.openConnection();
    connection.setRequestProperty("User-Agent", "");
    BufferedReader br = 
      new BufferedReader(new InputStreamReader(connection.getInputStream()));


    // Printout the resulting page
    String line = null;
    do
    {
      line = br.readLine();
      if (line != null)
        System.out.println(line);
      } while (line != null);

    br.close();
  }
}
