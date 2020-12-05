package domain.knowledge.base;

import java.util.regex.Pattern;

/**
 * Interface base para criação das conversas
 * @author Lucas Candalo
 */

public interface BaseConversationTopics {

    /**
     * Método que inicia uma conversa com o usuário
     * @return Texto de inicio do tópico da conversa
     */
    public String askConfirmation();
    /**
     * Método que checa a resposta enviada pelo usuário
     * @param answer Texto de resposta enviada pelo usuário
     * @return Texto de resposta do Bot de acordo com o que foi enviado pelo usuário
     */
    public String checkAnswer(String answer) throws Exception;

    public static final Pattern AFFIRMATIVE = Pattern.compile(".*(SIM|YES|VAMOS|BORA).*");
    public static final Pattern UNDECIDED = Pattern.compile(".*(N[AÃ]O\\ SEI|TALVEZ|PENS[AR|ANDO]).*");
    public static final Pattern NEGATIVE = Pattern.compile(".*(NÃO|NOT).*");
    public static final Pattern REQUEST_WEATHER = Pattern.compile("(\\D|\\ ){2,50},[A-Z]{2}");
}
