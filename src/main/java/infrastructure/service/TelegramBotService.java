package infrastructure.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.List;

public class TelegramBotService {

    TelegramBot telegramBot;
    GetUpdatesResponse updatesResponse;
    SendResponse sendResponse;
    BaseResponse baseResponse;
    int m;

    public TelegramBotService(String telegramToken){
        telegramBot = new TelegramBot(telegramToken);
        m = 0;
    }

    public void exec(){

        updatesResponse = telegramBot.execute(new GetUpdates().limit(100).offset(m));
        List<Update> updates = updatesResponse.updates();

        updates.forEach(update -> {
            m = update.updateId()+1;
            System.out.println("Recebendo mensagem:"+ update.message().text());

            baseResponse = telegramBot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
            System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

            sendResponse = telegramBot.execute(new SendMessage(update.message().chat().id(),"Não entendi..."));
            System.out.println("Mensagem Enviada?" + sendResponse.isOk());
        });
    }
}
