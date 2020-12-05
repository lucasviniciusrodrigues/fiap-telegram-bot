package adapter.infrastructure.models;

import java.util.List;
/**
 * Modelo para criação da resposta do clima
 * @author Marcio Campos
 */
public class WeatherResponse {
	public String temp;
	public String date;	
	public String description;
	public String city;
	public List<WeatherForecast> forecast;
}
