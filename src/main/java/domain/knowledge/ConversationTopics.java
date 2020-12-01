package domain.knowledge;

import adapter.infrastructure.models.WeatherForecast;
import adapter.infrastructure.models.WeatherResponse;
import adapter.infrastructure.service.WeatherService;
import domain.knowledge.base.BaseConversationTopics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public enum ConversationTopics implements BaseConversationTopics {

    FIRST_CONTACT {
        @Override
        public String askConfirmation() {
            setActiveContext(FIRST_CONTACT);
            return "Os assuntos que estou estudando são: Clima, data e 3\nQuer conversar sobre alguma dessas coisas? Caso queira, me fala o assunto ou envia a palavra tema que eu repito novamente a qualquer momento";
        }

        @Override
        public String checkAnswer(String answer) throws Exception {

            String response = "";

            if(AFFIRMATIVE.matcher(answer).matches())
                response = "Estou pronto para conversar, me fala o assunto que é mais fácil para organizar meus pensamentos";

            if(NEGATIVE.matcher(answer).matches())
                response = "Não quer falar? Que pena, gostaria de praticar minha conversação, se precisar de algo só chamar";

            if(!response.isEmpty()){
                setActiveContext(FIRST_CONTACT);
                return response;
            }

            throw new Exception();
        }

    },

    TEMA {
        @Override
        public String askConfirmation() {
            return "Posso repetir?";
        }

        @Override
        public String checkAnswer(String answer) throws Exception {

            String response = "";

            if(AFFIRMATIVE.matcher(answer).matches())
                response = "Consigo falar sobre: Clima, data e 3. Me fala sobre o que você quer falar";

            if(NEGATIVE.matcher(answer).matches())
                response = "Há, então tudo bem =D";

            if(!response.isEmpty()){
                setActiveContext(FIRST_CONTACT);
                return response;
            }

            throw new Exception();
        }

    },

    CLIMA {
        @Override
        public String askConfirmation() {
            return "Para saber o clima da sua cidade...\nDigite o nome da cidade seguido da UF separados por vírgula sem espaço\nExemplo: Campinas,SP";
        }

        @Override
        public String checkAnswer(String answer) throws Exception {

            String response = "";

            if(NEGATIVE.matcher(answer).matches())
                response = "Tudo bem, sem problemas ;)";

            else if(REQUEST_WEATHER.matcher(answer).matches()) {
            	WeatherService clima = new WeatherService();
            	WeatherResponse weather = clima.getCityWeather(answer);
            	
            	if (weather == null)
            		return  "Desculpe, não consegui consultar a previsão para a cidade " + answer;
            	
            	response = weather.city;
            	for (WeatherForecast forecast : weather.forecast) {
            		response += String.format("\nData: %s - %s\nmínima de %s˚ e máxima de %s˚\n", forecast.date, forecast.description, forecast.min, forecast.max);  
            	}
            }
            	

            if(!response.isEmpty()){
                setActiveContext(FIRST_CONTACT);
                return response;
            }

            return "Digite o nome da cidade seguido da UF separados por vírgula sem espaço\\nExemplo: Campinas,SP\"";
        }
    },

    DATA {

        private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        @Override
        public String askConfirmation() {
            return "Você quer saber a data atual?";
        }

        @Override
        public String checkAnswer(String answer) throws Exception {

            String response = "";

            if(AFFIRMATIVE.matcher(answer).matches())
                response = formatter.format(Calendar.getInstance().getTime());

            if(NEGATIVE.matcher(answer).matches())
                response = "Há, então tudo bem =D";

            if(!response.isEmpty()) {
                setActiveContext(FIRST_CONTACT);
                return response;
            }

            throw new Exception();
        }

    };

    private ConversationTopics activeContext;

    public ConversationTopics getActiveContext(){
        return activeContext;
    }

    public void setActiveContext(ConversationTopics activeContext){
         this.activeContext = activeContext;
    }


}
