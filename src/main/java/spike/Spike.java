package spike;

import javafx.fxml.FXML;
import spike.commands.Command;
import spike.commands.UpdateTaskCommand;
import spike.exceptions.SpikeException;
import spike.gui.DialogBox;
import spike.gui.MainWindow;
import spike.parser.Parser;
import spike.storage.Storage;
import spike.storage.TaskList;
import spike.ui.Ui;

/**
 * Spike is a simple task manager chatbot that helps users keep track of their tasks.
 * This is the entry point of the Spike application.
 * Initializes the application and starts the interaction with the user.
 */
public class Spike {
    private static final String DEFAULT_FILE_PATH = "data/spike.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Command currCommand;
    private static SpikeState currentState = SpikeState.COMPLETE;

    /**
     * Represents the state of the update command.
     */
    public enum SpikeState {
        UPDATE_AWAITING_REPLY,
        UPDATE_AWAITING_NEW_VALUE,
        COMPLETE
    }

    /**
     * Constructs a Spike object with the specified file path.
     * Initializes the storage, task list and user interface.
     *
     * @param filePath The file path to load and store the tasks.
     */
    public Spike(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (SpikeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a Spike object with the default file path.
     * Initializes the storage, task list and user interface.
     */
    public Spike() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Sets the state of the update command.
     *
     * @param state The state of the update command.
     */
    public static void setSpikeState(SpikeState state) throws SpikeException {
        currentState = state;
    }

    /**
     * Generates a response for the user's chat message.
     * Used for GUI applications.
     */
    public String getResponse(String input) {
        try {
            if (currentState == SpikeState.COMPLETE) {
                Command c = Parser.parse(input);
                if (c instanceof UpdateTaskCommand) {
                    currentState = SpikeState.UPDATE_AWAITING_REPLY;
                }
                c.execute(tasks, ui, storage);
                setCurrCommandType(c);
            } else if (currentState == SpikeState.UPDATE_AWAITING_REPLY) {
                UpdateTaskCommand currUpdateCommand = (UpdateTaskCommand) currCommand;
                currUpdateCommand.handleAwaitingReply(input, tasks, ui, storage);
            } else if (currentState == SpikeState.UPDATE_AWAITING_NEW_VALUE) {
                UpdateTaskCommand currUpdateCommand = (UpdateTaskCommand) currCommand;
                currUpdateCommand.handleAwaitingNewValue(input, tasks, ui, storage);
            }
            return ui.getStringToDisplay();
        } catch (SpikeException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Sets the current command type.
     *
     * @param currCommand The current command.
     */
    public void setCurrCommandType(Command currCommand) {
        this.currCommand = currCommand;
    }

    /**
     * Returns the current command type.
     *
     * @return The current command type.
     */
    public String getCurrCommandType() {
        return currCommand.getClass().getSimpleName();
    }

    /**
     * Starts the interaction with the user.
     * Reads the user input, processes the command and executes it.
     * Shows the result of the command to the user.
     * Stops the interaction when the user exits the application.
     * Used for CLI applications.
     */
    public void run() {
        ui.showHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SpikeException e) {
                ui.showExceptionMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Spike("data/spike.txt").run();
    }
}
