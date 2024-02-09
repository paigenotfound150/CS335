package genomeProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHelper {
	
	/* sendGET creates and sends an HTTP GET request to NCBI's E-Utils APi.
	 * It accepts an API query, formatted as a string, and prints out
	 * the response from the APi.*/
	public static void sendGET(String query) throws IOException {
		// Create connection and send request
		URL obj = new URL(query);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		
		System.out.println("GET Response Code :: " + responseCode);
		// Read response
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// If successful, print result
			System.out.println(response.toString());
		} else {
		System.out.println("GET request did not work");
		}
	}
}