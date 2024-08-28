package stan.commands;
import stan.TaskList;
import stan.Ui;
import stan.Storage;
import stan.Task;
import stan.StanMissingArgumentException;
import stan.StanInvalidArgumentException;
public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(String[] words) throws StanMissingArgumentException, StanInvalidArgumentException {
        if (words.length < 2) {
            throw new StanMissingArgumentException("You need to specify the task number to mark.");
        }
        try {
            this.taskIndex = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new StanInvalidArgumentException("The task number must be a valid integer.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StanInvalidArgumentException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new StanInvalidArgumentException("The task number is out of range.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}
