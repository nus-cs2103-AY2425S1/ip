package futureyou;

import java.io.IOException;

/**
 * Represents the main class of the Future You application.
 */
public class FutureYou {
    private final Ui ui;

    /**
     * Constructs an FutureYou instance with a new TaskList and Ui.
     *
     * @throws IOException if file does not exist
     */
    public FutureYou() throws IOException {
        ui = new Ui();
        initializeFile(ui);

    }

    /**
     * The main method that runs the FutreYou Program.
     *
     * @param args The command-line arguments.
     * @throws IOException if file does not exist
     */
    public static void main(String[] args) throws IOException {
        FutureYou futureYou = new FutureYou();
        futureYou.ui.hello();
        String userCommand = "";
        while (!userCommand.equals("bye")) {
            userCommand = futureYou.ui.readUserCommand();
            futureYou.ui.respond(userCommand);
        }
        futureYou.ui.close();
    }

    /**
     * Echos response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Future You heard: " + input;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String procressResponse(String userCommand) {
        return this.ui.respond(userCommand);
    }

    private void initializeFile(Ui ui) throws IOException {
        try {
            new TaskList("./data/futureYou.txt");
        } catch (IOException e) {
            String message = "Error when getting file: " + e.getMessage();
            ui.displayMessage(message);
        }
    }
}
