package nixy;

import java.util.function.Consumer;

import nixy.command.Command;
import nixy.command.CommandHistory;
import nixy.command.CommandType;
import nixy.command.UndoableCommand;
import nixy.exceptions.NixyException;
import nixy.parse.Parser;
import nixy.task.TaskList;
import nixy.ui.Ui;

/**
 * Todo list application that allows users to manage tasks.
 * Iterative project for CS2103
 */
public class Nixy {

    private Storage storage;
    private TaskList tasks;
    private CommandHistory commandHistory;
    private Ui ui;
    private Runnable onExit = () -> {};
    private Parser parser;

    /**
     * Constructor for Nixy.
     * Initializes the user interface, storage, task list, and parser.
     *
     * @param filePath The file path to store tasks data.
     */
    public Nixy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        commandHistory = new CommandHistory();
        try {
            tasks = new TaskList(storage.load());
        } catch (NixyException e) {
            ui.showNixyException(e);
            tasks = new TaskList();
        }
        parser = new Parser(storage, tasks, ui, commandHistory);
    }

    /**
     * Sets the display method for Nixy to display messages.
     *
     * @param display The display method to set.
     */
    public void setNewDisplay(Consumer<String[]> display) {
        ui.setDisplay(display);
    }

    /**
     * Sets the onExit method for Nixy to run when the application exits.
     *
     * @param onExit The onExit method to set.
     */
    public void setOnExit(Runnable onExit) {
        this.onExit = onExit;
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        ui.showWelcome();
    }

    /**
     * Processes user input and executes the corresponding command logic.
     *
     * @param userInput The user input to process.
     */
    public void processInput(String userInput) {
        Command c;
        try {
            c = parser.parse(userInput);
            assert c != null : "Parsed command should not be null";
        } catch (NixyException e) {
            ui.showNixyException(e);
            return;
        }

        boolean isExecuted = false;
        try {
            c.execute();
            isExecuted = true;
        } catch (NixyException e) {
            ui.showNixyException(e);
        }

        if (c instanceof UndoableCommand && isExecuted) {
            commandHistory.add((UndoableCommand) c);
        }

        if (c.getType() == CommandType.BYE) {
            onExit.run();
        }
    }

    /**
     * Main driver for Nixy CLI.
     * Entry point to display welcome message and read user input.
     */
    public void run() {
        // Have to use array to store boolean value to be able to modify it in lambda
        boolean[] isExit = { false };
        onExit = () -> isExit[0] = true;
        showWelcome();
        while (!isExit[0]) {
            String userInput = ui.readCliInput();
            processInput(userInput);
        }
    }

    public static void main(String[] args) {
        new Nixy("data/tasks.txt").run();
    }
}
