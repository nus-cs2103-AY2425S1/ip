package main.commands;
import java.io.IOException;
import main.source.*;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(taskIndex).unmarkAsDone();
            storage.saveTasks(tasks);  
            ui.unmarkTask(tasks.getTask(taskIndex));
        } catch (IOException e) {
            ui.showError("Unexpected error: " + e.getMessage());
        }
    }
}
