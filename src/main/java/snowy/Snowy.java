package snowy;

import snowy.common.Command;
import snowy.common.CommandResult;
import snowy.data.SnowyException;
import snowy.storage.Storage;
import snowy.tasklist.TaskList;
import snowy.ui.TextUi;
import snowy.parser.Parser;

public class Snowy {
    private static final String FILE_PATH = "../ip/src/main/data";
    private static final String FILENAME = "snowy.txt";

    private TextUi ui;
    private TaskList taskList;
    private Parser parser;

    public static void main(String... launchArgs) {
        new Snowy().run(launchArgs);
    }

    /** Runs the program until termination.  */
    public void run(String[] launchArgs) {
        start(launchArgs);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, loads up the data, and prints the welcome message.
     *
     * @param launchArgs arguments supplied by the user at program launch
     */
    private void start(String[] launchArgs) {
        Storage storage = new Storage(FILE_PATH, FILENAME);
        this.ui = new TextUi();
        this.taskList = new TaskList(storage);
        this.parser = new Parser();
        ui.showWelcomeMessage();
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        boolean isExit = false;

        while (!isExit) {
            try {
                String userCommandText = ui.getUserInput();
                command = parser.parseCommand(userCommandText);

                if (command != null) {
                    command.setData(taskList);
                    CommandResult result = command.execute();
                    ui.showTaskAddedMessage(result.getFeedbackToUser());
                    isExit = command.isExit();
                }

            } catch (SnowyException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }
}


