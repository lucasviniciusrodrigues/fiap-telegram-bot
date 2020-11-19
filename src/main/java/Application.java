import adapter.infrastructure.service.TelegramBotService;
import config.TelegramBotInitializer;

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
