package config;

import adapter.infrastructure.service.TelegramBotService;

import java.io.IOException;

public class TelegramBotInitializer {

    public static TelegramBotService initialize() throws IOException {
        try {
            return new TelegramBotService(ResourceManager.readProperty("telegram-token-access"));
        }
        catch (Exception e){
            System.out.println("Error at initializing telegram bot");
            throw e;
        }
    }

}
