package tbd.knowlodge;

import tbd.knowlodge.base.BaseConversationTopics;

public enum ConversationTopics implements BaseConversationTopics {

    FIRST_CONTACT {
        @Override
        public String askConfirmation() {
            return "Os assuntos que estou estudando são: 1, 2 e 3\nQuer conversar sobre alguma dessas coisas? Caso queira, me fala o assunto ou envia a palavra tema que eu repito novamente a qualquer momento";
        }

        @Override
        public String affirmative() {
            return "Estou pronto para conversar, me fala o assunto que é mais fácil para organizar meus pensamentos";
        }

        @Override
        public String negative() {
            return "Não quer falar? Que pena, gostaria de praticar minha conversação, se precisar de algo só chamar";
        }
    },

    CLIMA {
        @Override
        public String askConfirmation() {
            return "Você quer falar sobre o clima?";
        }

        @Override
        public String affirmative() {
            return "Clima Sim";
        }

        @Override
        public String negative() {
            return "Clima não";
        }
    },

    TEMA {
        @Override
        public String askConfirmation() {
            return "Posso repetir?";
        }

        @Override
        public String affirmative() {
            return "Consigo falar sobre: 1, 2 e 3. Me fala sobre o que você quer falar";
        }

        @Override
        public String negative() {
            return "Há, então tudo bem =D";
        }
    };


}
