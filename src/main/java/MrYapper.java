import java.util.Scanner;

public class MrYapper {
    private static final String GREETING_MESSAGE = " Hello! I'm MrYapper\n"
            + " What can I do for you?";
    private static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!";

    // Inserts line indentation in response messages
    private static void say(String message) {
        System.out.println("____________________________________________________________\n"
                + message
                + "\n____________________________________________________________");
    }

    private static void echo(String str) {
        say(" " + str);
    }

    public static void main(String[] args) {
        say(GREETING_MESSAGE);
        boolean conversationIsOngoing = true;
        Scanner userInputReader = new Scanner(System.in);

        do {
            String userInput = userInputReader.nextLine();

            if (userInput.equals("bye")) {
                conversationIsOngoing = false;
                userInputReader.close();
                say(GOODBYE_MESSAGE);
            } else {
                echo(userInput);
            }
        } while (conversationIsOngoing);
    }
}
