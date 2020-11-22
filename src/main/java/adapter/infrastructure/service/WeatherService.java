package adapter.infrastructure.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import adapter.infrastructure.models.WeatherResponse;


public class WeatherService {
	
	public WeatherResponse getCityWeather(String cityName) throws Exception {
		
		URL url = new URL("https://api.hgbrasil.com/weather?fields=only_results,temp,city_name,description,forecast,max,min,date,description&key=96e37c95&city_name=" + cityName);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() != 200) {
			throw new Exception("Erro ao consultar o serviço Weather");
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output = ""; 
		String json = "";
		
		while((output = br.readLine()) != null) {
			json += output;
		}
		
		conn.disconnect();
		
		Gson gson = new Gson();
		WeatherResponse weatherResponse = gson.fromJson(json, WeatherResponse.class);
		
		return weatherResponse;
	}

}
