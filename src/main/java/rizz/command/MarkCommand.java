package rizz.command;
import rizz.source.TaskList;
import rizz.source.Ui;
import rizz.source.Storage;
import java.io.IOException;



public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    } //cause 2nd item of list is Arr[1]

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try{
            tasks.getTask(taskIndex).markAsDone();
            storage.saveTasks(tasks);  
            ui.markTask(tasks.getTask(taskIndex));
        } catch (IOException e) {
            ui.showError("Unexpected error: " + e.getMessage());
        }
    }
}
