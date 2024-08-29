package commands;

import exceptions.InvalidCommandException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;
import ui.Ui;

import java.io.IOException;

public class AddTodoCommand extends Command {
    protected String description;

    public AddTodoCommand(String userInput) {
        this.description = userInput.substring(5).trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        if (description.isEmpty()) {
            throw new InvalidCommandException("Error: Missing task description\n\nPlease use the following format: todo <description>");
        }
        Task task = new Todo(description);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        try {
            storage.save(tasks.toArrayList());
        } catch (IOException e) {
            ui.showError("Error: Unable to save tasks to file");
        }
    }
}
