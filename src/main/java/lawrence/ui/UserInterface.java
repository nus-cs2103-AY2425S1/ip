package lawrence.ui;

/**
 * Represents the interface where messages can be displayed to the user in the console.
 */
public class UserInterface {
    private String name;

    /**
     * Constructor. The name of the bot can be specified for
     * the bot to identify itself to the user.
     *
     * @param name the name of the chatbot
     */
    public UserInterface(String name) {
        this.name = name;
    }

    /**
     * Displays a message greeting the user.
     */
    public void greetUser() {
        String greeting = String.format("Hello! I'm %s and I'm here to establish another GST hike.%n"
                + "What can I do for you?", name);
        showMessage(greeting);
    }

    /**
     * Displays an exit message.
     */
    public void showExitMessage() {
        showMessage("That's all folks! Hope to see you again soon!");
    }

    /**
     * Shows the specified message in the console.
     * Messages will be separated from each other using horizontal lines.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        showHorizontalLine();
        System.out.println(message);
        showHorizontalLine();
    }

    /**
     * Displays a horizontal line in the console.
     */
    public void showHorizontalLine() {
        System.out.println("====================");
    }
}
