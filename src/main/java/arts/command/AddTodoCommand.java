package arts.command;

import arts.task.TaskList;
import arts.task.Todo;
import arts.util.Storage;
import arts.util.Ui;
import arts.ArtsException;

public class AddTodoCommand implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final String description;

    public AddTodoCommand(TaskList tasks, Storage storage, Ui ui, String description) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.description = description;
    }

    @Override
    public void execute() throws ArtsException {
        if (description == null || description.trim().isEmpty()) {
            throw new ArtsException("The description of a todo cannot be empty.");
        }
        tasks.addTask(new Todo(description));
        storage.save(tasks.getTasks());
        ui.showMessage("Got it. I've added this task:\n " + tasks.getTask(tasks.size() - 1) +
                "\nNow you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
    }
}
