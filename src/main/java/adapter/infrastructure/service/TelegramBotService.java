package adapter.infrastructure.service;

import adapter.infrastructure.repository.TelegramBotRepository;
import com.pengrad.telegrambot.model.Update;

import java.util.List;

public class TelegramBotService {

    TelegramBotRepository telegramBotRepository;
    int offset;

    public TelegramBotService(String telegramToken){
        telegramBotRepository = new TelegramBotRepository(telegramToken);
        offset = 0;
    }

    public void exec(){

        List<Update> updates = telegramBotRepository.getMessages(offset);

        updates.forEach(update -> {
            offset = update.updateId() + 1;
            telegramBotRepository.sendTypingSignal(update);
            telegramBotRepository.sendMessage(update, "Não entendi");
        });
    }
}
