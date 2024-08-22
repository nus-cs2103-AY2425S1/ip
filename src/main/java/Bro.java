import java.util.Scanner;

public class Bro {
    final static String GREETING_MESSAGE = """
                 Hello! I'm Bro
                 What can I do for you?""";
    final static String GOODBYE_MESSAGE = "Goodbye.";
    final static String EXIT_COMMAND = "bye";
    final static String LIST_COMMAND = "list";

    public static void main(String[] args) {
        reply(GREETING_MESSAGE);
        // Start conversation
        Scanner sc = new Scanner(System.in);
        boolean isConversing = true;
        while (isConversing) {
            String input = sc.nextLine();
            switch (input) {
                case EXIT_COMMAND:
                    isConversing = false;
                    break;
                case LIST_COMMAND:
                default:

            }
        }
        reply(GOODBYE_MESSAGE);
    }

    // Prints a adds to list on standard output
    public static void reply(String content) {
        String replyStr = String.format("""
                ____________________________________________________________
                %s. Bro
                ____________________________________________________________
                """, content);
        System.out.print(replyStr);
    }
}
