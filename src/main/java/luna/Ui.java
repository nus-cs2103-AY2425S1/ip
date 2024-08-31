package luna;

import java.util.Scanner;

/**
 * Manages the UI of the chatbot.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Asks user to input command.
     *
     * @return Text input from user.
     * @throws LunaException If input is empty
     */
    public String readCommand() throws LunaException {

        String input = scanner.nextLine();

        if (input.isEmpty()) {
            throw new LunaException("""
                    Please enter one of the following commands:
                    "todo", "deadline", "event"
                    "mark", "unmark", "delete"
                    "list", "bye"
                    """);
        }

        return input;
    }

    /**
     * Prints line for separation between inputs and results.
     */
    public void showLine() {
        System.out.println("---------------------------------" + System.lineSeparator());
    }

    /**
     * Prints line for error.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(message);
    }

}
