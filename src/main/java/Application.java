import infrastructure.service.TelegramBotService;

import java.io.InputStream;
import java.util.Properties;

public class Application {

    public static void main(String[] args) {

        try(InputStream input = Application.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties properties = new Properties();
            properties.load(input);

            TelegramBotService telegramBotRepository = new TelegramBotService(properties.getProperty("telegram-token-access"));

            while (true) {
                telegramBotRepository.exec();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
