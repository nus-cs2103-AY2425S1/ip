package bob.command;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.tasks.Task;
import bob.tasks.Todo;
import bob.ui.Ui;

import java.util.ArrayList;

public class TodoCommand extends Command {

    private final String taskDescription;

    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException {
        if (taskDescription.isEmpty()) {
            throw new BobException("Description of the todo cannot be empty :(");
        }
        Task todo = new Todo(taskDescription);
        tasks.add(todo);
        storage.save(tasks);
        return ui.showAddedTask(todo, tasks.size());
    }
}
