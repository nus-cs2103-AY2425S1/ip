package michaelscott;

import michaelscott.command.Command;
import michaelscott.command.CommandParser;
import michaelscott.task.TaskList;
import michaelscott.utils.MichaelScottException;
import michaelscott.utils.Storage;
import michaelscott.utils.Ui;

/**
 * This class represents the entire chatbot named MichaelScott.
 * It manages the main execution loop, user interface, command parsing,
 * task storage, and error handling.
 */
public class MichaelScott {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final CommandParser parser;
    private String commandType;
    /**
     * Constructs a new MichaelScott chatbot instance.
     * Initializes the UI, command parser, task list, and storage components.
     */
    public MichaelScott() {
        ui = new Ui();
        parser = new CommandParser();
        tasks = new TaskList();
        storage = new Storage(tasks);
    }

    public String getResponse(String input) throws MichaelScottException {
        CommandParser parser = new CommandParser();
        Command c = parser.parse(input);
        String result = c.execute(tasks);
        this.commandType = c.getSimpleName();
        storage.saveTasks(tasks.getTasks());
        if (commandType.equals("ExitCommand")) {
            return "Exit-Signal";
        }
        return result;
    }

    public String getCommandType() {
        return commandType;
    }
}
