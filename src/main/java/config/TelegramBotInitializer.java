package config;

import adapter.infrastructure.service.TelegramBotService;

import java.io.IOException;

/**
 * Classe para incializar o telegrambot passando como parêmetro o token fornecido no arquivo application.properties
 *
 * @param telegram-token-access - Token de ativação do bot
 * @author Lucas Vinicius, Marcios Campos, Rafael Martins
 */

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
