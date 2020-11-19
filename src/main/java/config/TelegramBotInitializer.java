package config;

import adapter.infrastructure.service.TelegramBotService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TelegramBotInitializer {

    public static TelegramBotService initialize() throws IOException {

        try(InputStream input = TelegramBotInitializer.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties properties = new Properties();
            properties.load(input);

            return new TelegramBotService(properties.getProperty("telegram-token-access"));
        }
        catch (Exception e){
            System.out.println("Error at initializing telegram bot");
            throw e;
        }
    }

}
