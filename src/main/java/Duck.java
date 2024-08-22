public class Duck {
    private static final String CHATBOT_NAME = "Duck";
    private static final String TEXT_SEPARATOR = "____________________________________________________________\n";

    public static void main(String[] args) {
        // Generate greeting
        String greeting = TEXT_SEPARATOR
                + String.format(" Hello! I'm %s\n", CHATBOT_NAME)
                + " What can I do for you?\n"
                + TEXT_SEPARATOR;

        // Print greeting
        System.out.println(greeting);
    }
}
