package main.commands;
import java.io.IOException;
import main.source.*;


public class DeleteCommand extends Command {
    private int index;

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
