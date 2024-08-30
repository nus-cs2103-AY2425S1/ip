package bobbybot.commands;

import java.io.IOException;

import bobbybot.BobbyBotException;
import bobbybot.Storage;
import bobbybot.Task;
import bobbybot.TaskList;
import bobbybot.ToDo;
import bobbybot.ui.Ui;



/**
 * Represents a command to add a todo task.
 */
public class CommandTodo extends Command {

    private final String description;

    /**
     * Creates a new CommandTodo object.
     *
     * @param argument The argument string to create the todo task.
     * @throws BobbyBotException If the argument is empty.
     */
    public CommandTodo(String argument) throws BobbyBotException {
        if (argument.isEmpty()) {
            throw new BobbyBotException("The description of a todo cannot be empty.");
        }
        description = argument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyBotException {
        Task todo = new ToDo(description);
        tasks.addTask(todo);
        ui.printAddTask(tasks, todo);
        try {
            storage.saveTasksToFile(tasks.toArray());
        } catch (IOException e) {
            throw new BobbyBotException("Error saving to file.");
        }
    }
}
