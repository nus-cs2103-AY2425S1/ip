package strand.Commands;

import strand.Exceptions.StrandException;
import strand.Storage;
import strand.TaskList;
import strand.Tasks.Deadline;
import strand.Tasks.Event;
import strand.Tasks.Task;
import strand.Tasks.Todo;
import strand.Ui;

public class AddCommand extends Command {
    private final Task newTask;

    public AddCommand(String description) throws StrandException {
        this.newTask = new Todo(description);
    }

    public AddCommand(String description, String deadline) throws StrandException {
        this.newTask = new Deadline(description, deadline);
    }

    public AddCommand(String description, String start, String end) throws StrandException {
        this.newTask = new Event(description, start, end);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StrandException {
        tasks.addTask(this.newTask);
        ui.addTask(this.newTask, tasks.getNumOfTasks());
        storage.save(tasks.toFile());
    }
}
