package vinegar.command;

import vinegar.TaskList;
import vinegar.Validator;
import vinegar.VinegarException;
import vinegar.storage.Storage;
import vinegar.task.Task;
import vinegar.task.Todo;
import vinegar.ui.Ui;

import java.io.IOException;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String[] inputParts) throws VinegarException {
        Validator.validateParts(inputParts, 2, "Please specify the description of the todo task.");
        this.description = inputParts[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        Task todoTask = new Todo(description);
        tasks.addTask(todoTask);
        ui.printTaskAdded(todoTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
