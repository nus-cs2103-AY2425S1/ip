public class YappingBot {
    // Text strings
    private static final String BOT_NAME = "YappingBot";
    private static final String GREETING_TEXT = String.format(
            "Hello! I'm %s\nWhat can I do for you?",
            BOT_NAME
    );
    private static final String EXIT_TEXT = "Bye. Hope to see you again soon!";
    // End of text strings

    public static void main(String[] args) {
        System.out.println(GREETING_TEXT);
        System.out.println(EXIT_TEXT);
    }
}
