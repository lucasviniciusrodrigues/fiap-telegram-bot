import adapter.infrastructure.service.TelegramBotService;
import config.TelegramBotInitializer;

/*Classe para startar o servi�o do Telegram
 *O while � executado a cada 3 segundos mantendo o servi�o no AR
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
