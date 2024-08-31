package lbot.command;

import lbot.exception.ExecuteCommandException;
import lbot.exception.FileException;
import lbot.helper.Storage;
import lbot.helper.TaskList;
import lbot.helper.Ui;
import lbot.task.Todo;

/**
 * This class creates a new {@link Todo}.
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Public constructor for ToDoCommand
     *
     * @param description of the task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a new {@link Todo}
     *
     * @param ui      handles user input and printing.
     * @param storage handles reading and writing to text file.
     * @param tasks   contains list of tasks.
     * @throws ExecuteCommandException thrown if method is unable to create the task.
     * @throws FileException           thrown if there is an error encountered while reading or writing to file.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException {
        Todo todo = new Todo(this.description);
        tasks.addTask(todo);
        storage.saveTaskToFile(tasks);
        ui.printTaskAddedMessage(todo);
    }

    @Override
    public String toString() {
        return "todo command " + description;
    }
}
