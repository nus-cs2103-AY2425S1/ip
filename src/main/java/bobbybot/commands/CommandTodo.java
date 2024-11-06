package bobbybot.commands;

import bobbybot.BobbyBotException;
import bobbybot.Storage;
import bobbybot.TaskList;
import bobbybot.tasks.Task;
import bobbybot.tasks.ToDo;
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
        isUndoable = true;
        description = argument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyBotException {
        memento = new Memento(tasks);

        Task todo = new ToDo(description);
        tasks.addTask(todo);

        ui.printAddTask(tasks, todo);

        storage.saveTasksToFile(tasks.toArray());
    }
}
