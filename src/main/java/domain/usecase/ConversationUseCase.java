package domain.usecase;

import com.google.common.base.Enums;
import domain.knowledge.ConversationTopics;

public class ConversationUseCase {

    ConversationTopics conversationTopics;

    public String salute(){
        conversationTopics = ConversationTopics.FIRST_CONTACT;
        return "Olá, eu sou o Scoobot\n" + conversationTopics.askConfirmation();
    }

    public String getAnswer(String receveidMessage) throws Exception {
        try {
            String response = "";
            receveidMessage = receveidMessage.toUpperCase();

            if(conversationTopics.getActiveContext().equals(ConversationTopics.FIRST_CONTACT)) {
                conversationTopics = Enums.getIfPresent(ConversationTopics.class, receveidMessage)
                        .or(ConversationTopics.FIRST_CONTACT);

                if(conversationTopics.equals(ConversationTopics.FIRST_CONTACT))
                    throw new Exception();

                conversationTopics.setActiveContext(conversationTopics);
                response = conversationTopics.askConfirmation();
            }
            else {
                response = conversationTopics.checkAnswer(receveidMessage);
            }

            return response;

        } catch (Exception e) {
            return "Desculpe, não entendi";
        }
    }
}
