package domain.knowledge;

import adapter.infrastructure.models.WeatherForecast;
import adapter.infrastructure.models.WeatherResponse;
import adapter.infrastructure.service.WeatherService;
import domain.knowledge.base.BaseConversationTopics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Metodo de conversaÁ„o com o us·rio se basenado na resposta em caso de resposta afirmativa ou negativa
 * As respostas s„o enviadas para a clase baseConversationTopics.java e comparadas
 *
 * @param askConfirmation - Inicia a conversa baseado no tema escolhido
 * @param checkAnswer - Recebe a resposta do usu·rio para fazer a comparaÁ„o
 * @author Lucas Candalo, Marcios Campos, Rafael Martins
 */

public enum ConversationTopics implements BaseConversationTopics {

    FIRST_CONTACT {
        @Override
        public String askConfirmation() {
            setActiveContext(FIRST_CONTACT);
            return "Os assuntos que estou estudando s√£o: Clima, data, hora e se estiver com fome digite fome \nQuer conversar sobre alguma dessas coisas? Caso queira, me fala o assunto ou envia a palavra tema que eu repito novamente a qualquer momento";return "Os assuntos que estou estudando s√£o: Clima, data e 3\nQuer conversar sobre alguma dessas coisas? Caso queira, me fala o assunto ou envia a palavra tema que eu repito novamente a qualquer momento";
        }

        @Override
        public String checkAnswer(String answer) throws Exception {

            String response = "";

            if(AFFIRMATIVE.matcher(answer).matches())
                response = "Estou pronto para conversar, me fala o assunto que √© mais f√°cil para organizar meus pensamentos";

            if(NEGATIVE.matcher(answer).matches())
                response = "N√£o quer falar? Que pena, gostaria de praticar minha conversa√ß√£o, se precisar de algo s√≥ chamar";

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
                response = "Consigo falar sobre: Clima, data e 3. Me fala sobre o que voc√™ quer falar";

            if(NEGATIVE.matcher(answer).matches())
                response = "H√°, ent√£o tudo bem =D";

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
            return "Para saber o clima da sua cidade...\nDigite o nome da cidade seguido da UF separados por v√≠rgula sem espa√ßo\nExemplo: Campinas,SP";
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
            		return  "Desculpe, n√£o consegui consultar a previs√£o para a cidade " + answer;
            	
            	response = weather.city;
            	for (WeatherForecast forecast : weather.forecast) {
            		response += String.format("\nData: %s - %s\nm√≠nima de %sÀö e m√°xima de %sÀö\n", forecast.date, forecast.description, forecast.min, forecast.max);  
            	}
            }
            	

            if(!response.isEmpty()){
                setActiveContext(FIRST_CONTACT);
                return response;
            }

            return "Digite o nome da cidade seguido da UF separados por v√≠rgula sem espa√ßo\\nExemplo: Campinas,SP\"";
        }
    },

    DATA {

        private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        @Override
        public String askConfirmation() {
            return "Voc√™ quer saber a data atual?";
        }

        @Override
        public String checkAnswer(String answer) throws Exception {

            String response = "";

            if(AFFIRMATIVE.matcher(answer).matches())
                response = formatter.format(Calendar.getInstance().getTime());

            if(NEGATIVE.matcher(answer).matches())
                response = "H√°, ent√£o tudo bem =D";

            if(!response.isEmpty()) {
                setActiveContext(FIRST_CONTACT);
                return response;
            }

            throw new Exception();
        }

    },
  HORA {
    	
    	private final SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        @Override
        public String askConfirmation() {
            return "VocÍ quer saber a hora atual?";
        }

        @Override
        public String checkAnswer(String answer) throws Exception {
        	String date;
            String response = "";

            if(AFFIRMATIVE.matcher(answer).matches())
                response = formatter.format(Calendar.getInstance().getTime());
 
            if(NEGATIVE.matcher(answer).matches())
                response = "Sem problemas";
            
            if(!response.isEmpty()){
                setActiveContext(FIRST_CONTACT);
                return response;
            }

            return "";
        }
    },
    
 FOME {
        @Override
        public String askConfirmation() {
            return "VocÍ est· com fome ?";
        }

        @Override
        public String checkAnswer(String answer) throws Exception {
            String response = "";

            if(AFFIRMATIVE.matcher(answer).matches())
                response = "Tenho algumas sugestıes, quer ver ?";
                
            if(AFFIRMATIVE.matcher(answer).matches())
            	 response = "Tenho algumas opÁıes, por exemplo acesse e baixe o app para Android https://play.google.com/store/apps/details?id=br.com.brainweb.ifood&hl=pt_BR&gl=US";
            
            if(NEGATIVE.matcher(answer).matches())
                response = "Sem problemas";
            
            if(!response.isEmpty()){
                setActiveContext(FIRST_CONTACT);
                return response;
            }

            return "";
        }
    },
 ;

    private ConversationTopics activeContext;

    public ConversationTopics getActiveContext(){
        return activeContext;
    }

    public void setActiveContext(ConversationTopics activeContext){
         this.activeContext = activeContext;
    }


}
