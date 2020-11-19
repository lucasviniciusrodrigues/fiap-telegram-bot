package tbd;

import com.google.common.base.Enums;
import tbd.knowlodge.ConversationTopics;
import tbd.knowlodge.base.BaseConversationTopics;

import java.util.regex.Pattern;

public class Tbd {

    ConversationTopics conversationTopics;

    private static final Pattern AFFIRMATIVE = Pattern.compile("(SIM)|(AFIRMATIVO)|(CLARO)|(COM CERTEZA)");
    private static final Pattern NEGATIVE = Pattern.compile("(NAO)|(NUNCA)|(NEGATIV)|(NÃO)");


    public String salute(){
        conversationTopics = ConversationTopics.FIRST_CONTACT;
        return "Olá, eu sou o Scoobot\n" + ConversationTopics.FIRST_CONTACT.askConfirmation();
    }

    public String getAnswer(String receveidMessage) throws Exception {
        try {
            String response = "";
            receveidMessage = receveidMessage.toUpperCase();

            if(conversationTopics.equals(ConversationTopics.FIRST_CONTACT)) {
                conversationTopics = Enums.getIfPresent(ConversationTopics.class, receveidMessage)
                        .or(ConversationTopics.FIRST_CONTACT);

                if(conversationTopics.equals(ConversationTopics.FIRST_CONTACT))
                    throw new Exception();

                response = conversationTopics.askConfirmation();
            }
            else {
                response = checkYesOrNo(receveidMessage);
                conversationTopics = ConversationTopics.FIRST_CONTACT;
            }

            return response;

        } catch (Exception e) {
            conversationTopics = ConversationTopics.FIRST_CONTACT;
            return "Desculpe, não entendi";
        }
    }

    private String checkYesOrNo(String receveidMessage) throws Exception {
        if(AFFIRMATIVE.matcher(receveidMessage).matches())
            return conversationTopics.affirmative();

        if(NEGATIVE.matcher(receveidMessage).matches())
            return conversationTopics.negative();

        throw new Exception();
    }
}
