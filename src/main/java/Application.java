import adapter.infrastructure.service.TelegramBotService;
import config.TelegramBotInitializer;

/*Classe para startar o serviço do Telegram
 *O while é executado a cada 3 segundos mantendo o serviço no AR
 *@author Lucas Vinicius
*/

public class Application {

    public static void main(String[] args) {

        try {

            TelegramBotService telegramBotService = TelegramBotInitializer.initialize();

            while (true) {
                Thread.sleep(3000);
                telegramBotService.exec();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
