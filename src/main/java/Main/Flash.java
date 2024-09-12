package Main;

import Commands.Command;
import Data.Storage;
import Data.StoreList;
import Exceptions.InvalidIndexException;
import Parser.Parser;

/**
 * Entry point of the chatbot.
 * Initializes the application and starts the interaction with the user.
 */
public class Flash {
    private Ui ui;
    private StoreList storeList;
    private Storage storage;
    private final static String FILE_PATH = "./data/flash.txt";

    /* chatbot runs till user inputs bye */
    public void run() {
        start();
        runLoopTillExitCommand();
        exit();

    }

    /**
     * Sets up the required objects,
     * loads up the data from the storage file,
     * prints the intro message.
     *
     */
    private void start() {
            //initiliaze ui
            this.ui = new Ui();

            // list is instantiated with tasks loaded from storage
            this.storeList = new StoreList(storage.loadTasks());
            // show the starting message
            ui.showIntroMessage();
    }

    /* Prints goodbye and exits */
    private void exit() {
        // show exit message once user says bye
        ui.showExitMessage();
        ui.closeScanner();
        System.exit(0);

    }

    /* Reads and Executes user input till user says bye */
    private void runLoopTillExitCommand() {
        Command command;
        String userInput = ui.getUserInput();
        command = new Parser().makeSenseOfUserInput(userInput);

        // while the command is not equal to the exit command (the loop runs)
        while (!command.isExit()) {
            executeCommand(command);
            userInput = ui.getUserInput();
            command = new Parser().makeSenseOfUserInput(userInput);
        }
    }

    /**
     * Executes the command and outputs(prints) the result.
     *
     * @param command user command
     */
    private void executeCommand(Command command) {
        try {
            command.setData(storeList);
            command.execute();
            storage.saveTasksToFile(storeList.getItems());
        } catch (InvalidIndexException e) {
            System.out.println("Failed to execute request");
            throw new RuntimeException();

        }
    }

    public static void main(String[] args) {
        new Flash().run();
    }
}
