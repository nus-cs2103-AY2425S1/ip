import java.util.Scanner;

public class Bro {
    public static void main(String[] args) {
        String GREETING = """
                 Hello! I'm Bro
                 What can I do for you?""";
        String GOODBYE = "Goodbye.";
        String EXIT_COMMAND = "bye";
        reply(GREETING);
        // Start conversation
        Scanner sc = new Scanner(System.in);
        boolean isConversing = true;
        while (isConversing) {
            String input = sc.nextLine();
            if (input.equals(EXIT_COMMAND)) {
                isConversing = false;
            } else {
                reply(input);
            }
        }
        reply(GOODBYE);
    }

    // Prints a reply on standard output
    public static void reply(String content) {
        String replyStr = String.format("""
                ____________________________________________________________
                %s. Bro
                ____________________________________________________________
                """, content);
        System.out.print(replyStr);
    }
}
