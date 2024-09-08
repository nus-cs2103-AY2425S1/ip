package botty;

import java.util.HashMap;
import java.util.Map;

import botty.commands.Command;
import botty.commands.DeadlineCommand;
import botty.commands.DeleteCommand;
import botty.commands.EventCommand;
import botty.commands.ExitCommand;
import botty.commands.FindCommand;
import botty.commands.ListCommand;
import botty.commands.MarkCommand;
import botty.commands.ParsedInput;
import botty.commands.TodoCommand;
import botty.commands.UnmarkCommand;
import botty.exceptions.BottyException;
import botty.exceptions.UnknownCommandException;
import botty.storage.StorageHandler;
import botty.tasks.TaskManager;

/**
 * The main class
 */
public class Botty {
    // Map of command name to command instance
    private final Map<String, Command> commands;
    // Manager of the tasks
    private final TaskManager taskManager;
    // Storage handler to handle the saving and loading
    private final StorageHandler storageHandler;

    /**
     * Constructs a {@code Botty} instance
     */
    public Botty() {
        commands = new HashMap<>();
        commands.put("list", new ListCommand());
        commands.put("mark", new MarkCommand());
        commands.put("unmark", new UnmarkCommand());
        commands.put("todo", new TodoCommand());
        commands.put("deadline", new DeadlineCommand());
        commands.put("event", new EventCommand());
        commands.put("delete", new DeleteCommand());
        commands.put("find", new FindCommand());
        commands.put("bye", new ExitCommand());

        taskManager = new TaskManager();
        storageHandler = new StorageHandler("./data", "tasks");

        loadTaskList();
    }

    /**
     * Returns if the input is the exit command
     * @param parsedInput the parsed user input
     * @return true if the input is the exit command
     */
    public boolean isExitCommand(ParsedInput parsedInput) {
        if (!commands.containsKey(parsedInput.getCommand())) {
            return false;
        }
        Command command = commands.get(parsedInput.getCommand());
        return command.isExit();
    }

    /**
     * Returns Botty's response to a given user input
     * @param parsedInput the parsed user input
     * @return Botty's response
     */
    public String getResponse(ParsedInput parsedInput) {
        try {
            if (!commands.containsKey(parsedInput.getCommand())) {
                throw new UnknownCommandException(parsedInput.getCommand());
            }

            Command command = commands.get(parsedInput.getCommand());
            String reply = command.execute(taskManager, parsedInput);

            if (command.changesTaskList()) {
                storageHandler.saveTaskList(taskManager);
            }

            return reply;
        } catch (BottyException exception) {
            return exception.getMessage();
        }
    }


    /**
     * Loads the task list from local storage into the task manager
     */
    private void loadTaskList() {
        try {
            storageHandler.loadTaskList(taskManager);
        } catch (BottyException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
