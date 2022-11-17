package com.imbdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class App {
	
	//Definindo a conexão
	private static HttpURLConnection connection;
	
	public static void main(String[] args) {
		
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();
		
		try {
			
			//Definindo o URL
			URL url = new URL("https://imdb-api.com/top-250-movies");
			//Abrindo a conexão e lançando uma catch clause
			connection = (HttpURLConnection) url.openConnection();
			
			//Request setup
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			int status = connection.getResponseCode();
			//System.out.println(status);
			
			
			//Verificando a resposta do endpoint	
			if (status > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				
				while((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				 reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				while((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			
			System.out.println(responseContent.toString());
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}

	}

}
