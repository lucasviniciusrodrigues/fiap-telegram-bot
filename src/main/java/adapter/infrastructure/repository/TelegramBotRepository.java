package adapter.infrastructure.repository;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import java.util.List;

public class TelegramBotRepository {

    TelegramBot telegramBot;
    GetUpdatesResponse updatesResponse;

    public TelegramBotRepository(String telegramToken){
        telegramBot = new TelegramBot(telegramToken);
    }

    public List<Update> getMessages(int offset){
        updatesResponse = telegramBot.execute(new GetUpdates().limit(100).offset(offset));
        return updatesResponse.updates();
    }

    public void sendTypingSignal(Update update){
        telegramBot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
    }

    public void sendMessage(Update update, String message){
        telegramBot.execute(new SendMessage(update.message().chat().id(), message));
    }

}
