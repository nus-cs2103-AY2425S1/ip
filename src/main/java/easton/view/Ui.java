package easton.view;

/**
 * Represents the user interface of the program.
 */
public class Ui {

    private static final String CHATBOT_NAME = "Easton";

    public static void divider() {
        displayToConsole("____________________________________________________________");
    }

    /**
     * Prints welcome message.
     */
    public static void welcome() {
        String logo = " _______  _______  _______  _______  _______  __    _\n"
                + "|       ||   _   ||       ||       ||       ||  |  | |\n"
                + "|    ___||  |_|  ||  _____||_     _||   _   ||   |_| |\n"
                + "|   |___ |       || |_____   |   |  |  | |  ||       |\n"
                + "|    ___||       ||_____  |  |   |  |  |_|  ||  _    |\n"
                + "|   |___ |   _   | _____| |  |   |  |       || | |   |\n"
                + "|_______||__| |__||_______|  |___|  |_______||_|  |__|\n";
        displayToConsole("Hello from\n" + logo);
        divider();
        displayToConsole("Hello! I'm " + CHATBOT_NAME);
        displayToConsole("What can I do for you?");
        divider();
    }

    /**
     * Displays text to console.
     *
     * @param text Text to be printed
     */
    public static void displayToConsole(String text) {
        System.out.println(text);
    }
}
