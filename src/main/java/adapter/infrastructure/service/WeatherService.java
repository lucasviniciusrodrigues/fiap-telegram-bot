package adapter.infrastructure.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;
import adapter.infrastructure.models.WeatherResponse;
import config.ResourceManager;


public class WeatherService {
	
	public WeatherResponse getCityWeather(String cityName) throws Exception {
		URL url = new URL(ResourceManager.readProperty("weather-url") + URLEncoder.encode(cityName, StandardCharsets.UTF_8));
		
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
