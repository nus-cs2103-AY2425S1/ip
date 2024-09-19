package potong;

import potong.command.Command;
import potong.command.ExitCommand;

/**
 * Class to encapsulate a chatbot to store tasks.
 */
public class Potong {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String commandType;

    /**
     * Initialises the chatbot.
     */
    public Potong() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(tasks);
    }

    /**
     * Starts up the chatbot for user.
     */
    public void run() {
        while (true) {
            String userInput = ui.getUserCommand();
            Command c = Parser.parse(userInput);
            assert c != null;
            String message = c.execute(tasks, storage, ui);
            if (c instanceof ExitCommand) {
                break;
            }
        }
    }

    /**
     * Gets the response from the chatbot after receiving the user's input. This response is to be shown in the GUI.
     *
     * @param userInput Input from the user.
     * @return String response from the chatbot.
     */
    public String getResponse(String userInput) {
        Command c = Parser.parse(userInput);
        assert c != null;
        String message;
        message = c.execute(tasks, storage, ui);
        commandType = c.getClass().getSimpleName();
        return message;
    }

    public String getCommandType() {
        return commandType;
    }
    public static void main(String[] args) {
        new Potong().run();
    }
}
