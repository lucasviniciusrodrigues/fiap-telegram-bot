package config;

import adapter.infrastructure.service.TelegramBotService;

import java.io.IOException;

/**
 * Classe para incializar o telegrambot passando como parâmetro o token fornecido no arquivo application.properties
 * @author Lucas Vinicius, Marcios Campos, Rafael Martins
 */

public class TelegramBotInitializer {
    /**
     * Método que inicializa o Bot
     * @return Nova instância do serviço de Bot
     * @throws IOException Erro ao ler o arquivo de propriedades e ao inicializar o Bot
     */
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
