package jay;

/**
 * Represents the user interface of the chatbot.
 */
public class Ui {
    /**
     * Print the quit message.
     */
    public void quit() {
        System.out.println(formattedCommand("Bye. Hope to see you again soon!"));
    }


    /**
     * Print the message in a nice format
     * @param output The message to be printed
     */
    public void output(String output) {
        System.out.println(formattedCommand(output));
    }

    private String formattedCommand(String command) {
        return "____________________________________________________________\n"
                + command + "\n"
                + "____________________________________________________________\n";
    }
}
