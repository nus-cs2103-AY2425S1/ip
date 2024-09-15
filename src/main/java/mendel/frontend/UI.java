package mendel.frontend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The class handles all interactions with the user by displaying messages to the console.
 * It provides methods for displaying a welcome message and printing formatted output
 */
public class UI {
    /**
     * Constructs a UI object and prints a greeting message with the application logo.
     */
    public UI() {
        String logo = "Mendel";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Displays first message to the user.
     */
    public String showWelcome(Parser taskManager) {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String message = String.format("Hello! I'm Mendel\nWhat can I do for you?\n\nPssssssst..... BTW\n\n%s",
                taskManager.manage("remind " + date));
        System.out.println(this.wrapLines(message));
        return message;
    }

    /**
     * Prints a message to the console, formatted with borders.
     *
     * @param message The message to be printed.
     */
    public void preetyPrint(String message) {
        System.out.println(this.wrapLines(message));
    }

    /**
     * Wraps a message with a top and bottom border.
     *
     * @param message The message to be wrapped.
     * @return The wrapped message with borders.
     */
    public String wrapLines(String message) {
        return "____________________________________________________________\n" + message.indent(3)
                + "____________________________________________________________";
    }
}
