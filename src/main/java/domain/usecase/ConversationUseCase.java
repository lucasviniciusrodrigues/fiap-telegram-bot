package domain.usecase;

import com.google.common.base.Enums;
import domain.knowledge.ConversationTopics;
/**
 * Classe que inicia e recebe as mensagens para conversação
 * @author Lucas Vinicius
 */

public class ConversationUseCase {

    ConversationTopics conversationTopics;

    /**
     * Método da saudação ao usuário
     * @return Texto de saudação
     */
    public String salute(){
        conversationTopics = ConversationTopics.FIRST_CONTACT;
        return "Olá, eu sou o Scoobot\n" + conversationTopics.askConfirmation();
    }

    /**
     * Método que analisa a resposta recebida da conversa
     * @param receveidMessage Texto da resposta recebida
     * @return Próximo texto de continuação da conversa de acordo com a resposta do usuário
     */
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
