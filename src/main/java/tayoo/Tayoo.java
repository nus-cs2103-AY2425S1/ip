package tayoo;

import tayoo.command.Command;
import tayoo.exception.TayooException;

/**
 * The Tayoo class is the main class of the Tayoo chatbot, it initialises a new Ui, Storage and Tasklist per instannce
 * of Tayoo.
 */
public class Tayoo {
    private static final String NAME = "tayoo";
    private Ui ui;
    private Storage storage;
    private Tasklist tasks;
    private boolean isExit = false;
    private String commandType;

    /**
     * Constructs a new Tayoo instance by initializing a new Ui, Storage and Tasklist object.
     *
     * <p>If no Tasklist.txt file is detected (either because this is the first time a user is using the bot, or because
     * the file was deleted) the chatbot automatically creates a new .txt file in which to store data. Alternatively, if
     * a .txt file is found, the storage instance will read from the .txt file to load any tasks created when the user
     * previously used the bot.</p>
     *
     * <p>Any exceptions during file creation are caught and an error message is displayed.</p>
     *
     *
     */
    public Tayoo() {
        try {
            this.storage = new Storage();
            this.ui = new Ui(NAME);
            if (storage.createTxt()) {
                ui.printText("Creating a new tasklist.txt for you.");
            }
            this.tasks = new Tasklist(storage.returnTaskListFromTxt());
        } catch (TayooException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Starts the main loop of the Tayoo chatbot, handling user commands and interactions.
     *
     * <p>The method initializes the chatbot by displaying the welcome message then enters a loop where it
     * continuously reads the users input, processes commands and executes the corresponding actions. The loop continues
     * until a command that signals an exit is encountered.</p>
     *
     * <p>Exceptions that occur during the command execution are caught and handled by displaying an error message
     * to the user. The loop terminates when a command returns a signal to exit</p>
     */
    public void run() {
        ui.showWelcome();
        while (!this.isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                this.isExit = c.isExit();
            } catch (TayooException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * This method returns the string response of the chatbot for a given input command. This method also executes the
     * given command as if input to the textUi.
     *
     * @param input The user input to the GUI
     * @return The string response of the chatbot after having parsed and executed the input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            String toReturn = c.guiExecute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            this.isExit = c.isExit();
            return toReturn;
        } catch (TayooException e) {
            return e.getMessage();
        }
    }

    public boolean getIsExit() {
        return isExit;
    }


    public static void main(String[] args) {
        Tayoo tayoo = new Tayoo();
        tayoo.run();
        System.exit(0);
    }

    public String getCommandType() {
        return commandType;
    }

}
