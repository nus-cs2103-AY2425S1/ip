package lict.command;

import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.LictException;
import lict.task.Task;
import lict.task.Todo;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws LictException {
        if (description.isEmpty()) {
            throw new LictException("OOPS!!! The description of a todo cannot be empty. Please ensure that your input is in the format: todo {description}");
        }
        Task newTask = new Todo(description);
        tasks.addTask(newTask);
        ui.hasAddedTask(newTask, tasks.size());
        storage.save(tasks);
    }
}
