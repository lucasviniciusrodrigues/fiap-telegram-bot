package tbd.knowlodge.base;

import java.util.regex.Pattern;

public interface BaseConversationTopics {
    public String askConfirmation();
    public String checkAnswer(String answer) throws Exception;

    public static final Pattern AFFIRMATIVE = Pattern.compile(".*(SIM|YES|VAMOS|BORA).*");
    public static final Pattern UNDECIDED = Pattern.compile(".*(N[AÃ]O\\ SEI|TALVEZ|PENS[AR|ANDO]).*");
    public static final Pattern NEGATIVE = Pattern.compile(".*(NÃO|NOT).*");
    public static final Pattern REQUEST_WEATHER = Pattern.compile("(\\D|\\ ){2,50},[A-Z]{2}");
}
