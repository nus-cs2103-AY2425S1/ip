/**
 * Handles printing and reading input from user.
 *
 * @author dwsc37
 */
public class Ui {
    private void printMessageWithDividers(String message) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public void printWelcomeMessage() {
        String welcomeMessage = "Hello, I'm BotManager, your friendly task assistant!\nWhat can I do for you?";
        printMessageWithDividers(welcomeMessage);
    }

    public void printGoodbyeMessage() {
        String goodbyeMessage = "Goodbye! Hope to see you again soon!";
        printMessageWithDividers(goodbyeMessage);
    }

}
