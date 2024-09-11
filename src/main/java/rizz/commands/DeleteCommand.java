package rizz.commands;
import rizz.source.TaskList;
import rizz.source.Ui;
import rizz.source.Storage;
import java.io.IOException;



public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.deleteTask(index - 1);  
            storage.saveTasks(tasks); 
        } catch (IOException e) {
            ui.showError("Unexpected error: " + e.getMessage());
        } 
    }
}
