package tbd.knowlodge;

import tbd.knowlodge.base.BaseConversationTopics;

public enum ConversationTopics implements BaseConversationTopics {

    FIRST_CONTACT {
        @Override
        public String askConfirmation() {
            setActiveContext(FIRST_CONTACT);
            return "Os assuntos que estou estudando são: 1, 2 e 3\nQuer conversar sobre alguma dessas coisas? Caso queira, me fala o assunto ou envia a palavra tema que eu repito novamente a qualquer momento";
        }

        @Override
        public String checkAnswer(String answer) throws Exception {

            String response = "";

            if(AFFIRMATIVE.matcher(answer).matches())
                response = "Estou pronto para conversar, me fala o assunto que é mais fácil para organizar meus pensamentos";

            if(NEGATIVE.matcher(answer).matches())
                response = "Não quer falar? Que pena, gostaria de praticar minha conversação, se precisar de algo só chamar";

            if(!response.isEmpty()){
                setActiveContext(FIRST_CONTACT);
                return response;
            }

            throw new Exception();
        }

    },

    CLIMA {
        @Override
        public String askConfirmation() {
            return "Você quer falar sobre o clima?";
        }

        @Override
        public String checkAnswer(String answer) throws Exception {

            String response = "";

            if(AFFIRMATIVE.matcher(answer).matches())
                response = "Clima sim";

            if(NEGATIVE.matcher(answer).matches())
                response = "Clima não";

            if(!response.isEmpty()){
                setActiveContext(FIRST_CONTACT);
                return response;
            }

            throw new Exception();
        }
    },

    TEMA {
        @Override
        public String askConfirmation() {
            return "Posso repetir?";
        }

        @Override
        public String checkAnswer(String answer) throws Exception {

            String response = "";

            if(AFFIRMATIVE.matcher(answer).matches())
                response = "Consigo falar sobre: 1, 2 e 3. Me fala sobre o que você quer falar";

            if(NEGATIVE.matcher(answer).matches())
                response = "Há, então tudo bem =D";

            if(!response.isEmpty()){
                setActiveContext(FIRST_CONTACT);
                return response;
            }

            throw new Exception();
        }

    };

    private ConversationTopics activeContext;

    public ConversationTopics getActiveContext(){
        return activeContext;
    }

    public void setActiveContext(ConversationTopics activeContext){
         this.activeContext = activeContext;
    }


}
