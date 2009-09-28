/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package camerasearchxml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author csimoes
 */
public class WebSucker {

	private void WebSucker() {
	}

	public static String getPage(String url) throws IOException {
		String pageString = null;
		try {
			URL pageUrl = new URL(url);
			URLConnection connection = pageUrl.openConnection();
			connection.setRequestProperty("User-Agent", "");
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			do {
				line = br.readLine();
				if (line != null) {
					pageString.concat(line);
				}
			} while (line != null);
			br.close();
		} catch (MalformedURLException ex) {
			Logger.getLogger(WebSucker.class.getName()).log(Level.SEVERE, null, ex);
		}
		return pageString;
	}
}
