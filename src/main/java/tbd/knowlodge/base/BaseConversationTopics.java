package tbd.knowlodge.base;

import java.util.regex.Pattern;

public interface BaseConversationTopics {
    public String askConfirmation();
    public String checkAnswer(String answer) throws Exception;

    public static final Pattern AFFIRMATIVE = Pattern.compile("(SIM)|(AFIRMATIVO)|(CLARO)|(COM CERTEZA)");
    public static final Pattern NEGATIVE = Pattern.compile("(NAO)|(NUNCA)|(NEGATIV)|(NÃO)");
}
