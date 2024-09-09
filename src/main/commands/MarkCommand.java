package main.commands;
import java.io.IOException;
import main.source.*;


public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

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
