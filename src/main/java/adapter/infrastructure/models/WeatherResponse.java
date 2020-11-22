package adapter.infrastructure.models;

import java.util.List;

public class WeatherResponse {
	public String temp;
	public String date;	
	public String description;
	public String city_name;
	public List<WeatherForecast> forecast;
}
