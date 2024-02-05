package genomeProject;

import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		String header = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
		String command = "efetch.fcgi?db=nuccore&id=KR132597.1&rettype=fasta&retmode=json";
		String query = header.concat(command);
		sendGET(query);
		
	}
	
	/* The sendGET method creates and sends an HTTP GET request to NCBI's database.
	 * It accepts a string of UIDs delimited by commas, and prints out
	 * the response from the APi, which is formatted using JSON.
	 */
	private static void sendGET(String query) throws IOException {
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

