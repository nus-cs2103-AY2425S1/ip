package Commands;

import UI.Ui;
import Main.TaskList;
import Tasks.Task;
import Tasks.Deadline;
import Storage.Storage;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String taskDescription;
    private final LocalDateTime dueWhen;

    public DeadlineCommand(String taskDescription, LocalDateTime dueWhen) {
        this.taskDescription = taskDescription;
        this.dueWhen = dueWhen;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskDescription == null || this.taskDescription.trim().isEmpty()) {
            ui.showError("deadline WHAT???");
        } else if (this.dueWhen == null) {
            ui.showError("WHEN IS THE DEADLINE???\nPlease use yyyy-MM-dd HHmm format.");
        } else {
            Task newTask = new Deadline(this.taskDescription, this.dueWhen);
            taskList.addTask(newTask);
            int numTasks = taskList.size();
            ui.showMessage("Got it. I've added this task:");
            ui.showMessage("  " + newTask.toString());
            ui.showMessage("Now you have " + numTasks + " tasks in the list");
            storage.saveTasks(taskList.getTasks());
        }
    }
}
