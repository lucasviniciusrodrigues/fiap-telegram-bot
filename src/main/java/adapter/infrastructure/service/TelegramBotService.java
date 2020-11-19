package adapter.infrastructure.service;

import adapter.infrastructure.repository.TelegramBotRepository;
import com.pengrad.telegrambot.model.Update;
import tbd.Tbd;

import java.util.List;

public class TelegramBotService {

    TelegramBotRepository telegramBotRepository;
    Tbd tbd;
    int offset;

    public TelegramBotService(String telegramToken){
        telegramBotRepository = new TelegramBotRepository(telegramToken);
        tbd = new Tbd();
        offset = 0;
    }

    public void exec(){

        List<Update> updates = telegramBotRepository.getMessages(offset);

        try {

            if(offset == 0 && !updates.isEmpty())
                sendMessage(updates.get(0), tbd.salute());
            else
                for(Update update : updates)
                    sendMessage(update, tbd.getAnswer(update.message().text()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(Update update, String message) {
        this.offset = update.updateId() + 1;
        telegramBotRepository.sendTypingSignal(update);
        telegramBotRepository.sendMessage(update, message);
    }
}
