package stan.commands;
import stan.TaskList;
import stan.Ui;
import stan.Storage;
import stan.Task;
import stan.Todo;
import stan.StanMissingArgumentException;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String[] words) throws StanMissingArgumentException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new StanMissingArgumentException("The description of a todo cannot be empty.");
        }
        this.description = words[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}

