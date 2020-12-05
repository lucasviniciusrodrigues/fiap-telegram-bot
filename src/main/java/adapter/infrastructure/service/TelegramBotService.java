package adapter.infrastructure.service;

import adapter.infrastructure.repository.TelegramBotRepository;
import com.pengrad.telegrambot.model.Update;
import domain.usecase.ConversationUseCase;

import java.util.List;

/**
 * Classe de ligação da aplicação com o Bot do Telegram
 * @author Lucas Vinicius
 */
public class TelegramBotService {

    TelegramBotRepository telegramBotRepository;
    ConversationUseCase tbd;
    int offset;

    /**
     * Construtor da classe
     * @param telegramToken Token para autenticação com o Bot
     */
    public TelegramBotService(String telegramToken){
        telegramBotRepository = new TelegramBotRepository(telegramToken);
        tbd = new ConversationUseCase();
        offset = 0;
    }

    /**
     * Execução do serviço de comunicação com o Bot
     */
    public void exec(){
        List<Update> updates = telegramBotRepository.getMessages(offset);

        try {
            if(offset == 0 && !updates.isEmpty())
                sendMessage(updates.get(0), tbd.salute());
            else
            	if (updates != null)
            		for(Update update : updates)
            			sendMessage(update, tbd.getAnswer(update.message().text()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para envio de mensagens para o Bot
     * @param update Objeto com as informações da conversa
     * @param message Texto da mensagem a ser enviada
     */
    private void sendMessage(Update update, String message) {    	
        this.offset = update.updateId() + 1;
        telegramBotRepository.sendTypingSignal(update);
        telegramBotRepository.sendMessage(update, message);
    }
}
