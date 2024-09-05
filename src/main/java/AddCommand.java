import java.io.IOException;

public class AddCommand extends Command {
    private String taskDescription;
    private Parser.TaskTypes taskType = Parser.TaskTypes.UNDEFINED;

    public AddCommand(String taskDescription, Parser.TaskTypes taskType) {
        this.taskDescription = taskDescription;
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = null;
        switch (taskType) {
        case TODO:
            task = new Todo(taskDescription);
            break;
        case DEADLINE:
            task = new Deadline(taskDescription);
            break;
        case EVENT:
            task = new Event(taskDescription);
            break;
        }
        tasks.add(task);
        ui.addTask(task, tasks.size());
        storage.save(tasks);
    }
}
