public class Oyster {
    public static final String CHATBOT_EMOJI = "\uD83E\uDDAA";
    public static final String CHATBOT_NAME = "OYSTER CHATBOT " + CHATBOT_EMOJI;
    private static final LogicController logic = new LogicController();

    public static void main(String[] args) {
        logic.begin();
    }
}
