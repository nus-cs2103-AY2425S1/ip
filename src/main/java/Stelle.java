public class Stelle {
    static final String NAME = "Stelle";

    public static void main(String[] args) {
        ChatLogic chatLogic = new ChatLogic(NAME);

        chatLogic.printGreeting();
        chatLogic.printBye();
    }
}
