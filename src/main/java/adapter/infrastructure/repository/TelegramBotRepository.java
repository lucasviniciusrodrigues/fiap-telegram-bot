package adapter.infrastructure.repository;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import java.util.List;

/**
 * Classe utilizada para iniciar o dialogo com o cliente
 * Responsável por receber os updates do usuário e enviar as respostas
 * @param getMessage usado para pegar a resposta do usuário
 * TelegramBotRepository para pegar a configuração de token do bot no telegram
 * @author Lucas Vinicius
 */

public class TelegramBotRepository {

    TelegramBot telegramBot;
    GetUpdatesResponse updatesResponse;

    /**
     * Método construtor que cria a instância do Bot
     * @param telegramToken - Token do serviço bot do Telegram
     */
    public TelegramBotRepository(String telegramToken){
        telegramBot = new TelegramBot(telegramToken);
    }

    /**
     * Método que seleciona a resposta do usuário
     * @param offset - Id da conversa
     * @return Resposta do usuário para o Bot
     */
    public List<Update> getMessages(int offset){
        updatesResponse = telegramBot.execute(new GetUpdates().limit(100).offset(offset));
        return updatesResponse.updates();
    }

    /**
     * Método que envia o sinal para o Telegram de que a informação está sendo escrita
     * @param update Objeto com as informações da conversa
     */
    public void sendTypingSignal(Update update){
        telegramBot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
    }

    /**
     * Método que envia a mensagem para o Bot
     * @param update - Objeto com as informações da conversa
     * @param message - Mensagem a ser enviada para o Bot
     */
    public void sendMessage(Update update, String message){
        telegramBot.execute(new SendMessage(update.message().chat().id(), message));
    }

}
