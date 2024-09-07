package bobbybot;

import java.util.ArrayList;

import bobbybot.commands.Command;
import bobbybot.tasks.Task;
import bobbybot.ui.Ui;

/**
 * BobbyBot is a chatbot that helps you keep track of your tasks.
 */
public class BobbyBot {
    protected static final String NAME = "BobbyBot";
    protected static final String FILE_PATH = "./data/bobbybot.txt";
    protected TaskList tasks;
    protected final Ui ui;
    protected final Storage storage;

    private ArrayList<Command> commandStack = new ArrayList<>();

    /**
     * Creates an instance of BobbyBot.
     */
    public BobbyBot() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            ArrayList<Task> taskArray = storage.getTasksFromFile();
            tasks = new TaskList(taskArray);
        } catch (Exception e) {
            ui.printResponse("Error reading from file. Starting with an empty task list.");
            tasks = new TaskList();
        }

        assert tasks != null;
    }

    /**
     * Gets the name of BobbyBot.
     */
    public String getName() {
        return NAME;
    }

    /**
     * Inputs a command to BobbyBot.
     * @param input The command to input.
     */
    public void executeInput(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            if (command.isUndo()) {
                if (commandStack.isEmpty()) {
                    throw new BobbyBotException("There are no commands to undo.");
                }
                Command lastCommand = commandStack.remove(commandStack.size() - 1);
                lastCommand.unExecute(tasks, ui, storage);
            }

            if (command.isUndoable()) {
                commandStack.add(command);
            }
        } catch (BobbyBotException e) {
            ui.printError(e);
        }
    }
}
