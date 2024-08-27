package Commands;

import Main.TaskList;
import UI.Ui;
import Tasks.Task;
import Tasks.Event;
import Storage.Storage;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String taskDescription;
    private final LocalDateTime startWhen;
    private final LocalDateTime endWhen;

    public EventCommand(String taskDescription, LocalDateTime startWhen, LocalDateTime endWhen) {
        this.taskDescription = taskDescription;
        this.startWhen = startWhen;
        this.endWhen = endWhen;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskDescription == null || this.taskDescription.trim().isEmpty()) {
            ui.showError("deadline WHAT???");
        } else if (this.startWhen == null) {
            ui.showError("WHEN DOES IT START???\nPlease use yyyy-MM-dd HHmm format.");
        } else if (this.endWhen == null) {
            ui.showError("WHEN DOES IT END???\nPlease use yyyy-MM-dd HHmm format.");
        } else {
            Task newTask = new Event(this.taskDescription, this.startWhen, this.endWhen);
            taskList.addTask(newTask);
            int numTasks = taskList.size();

            ui.showMessage("Got it. I've added this task:");
            ui.showMessage("  " + newTask.toString());
            ui.showMessage("Now you have " + numTasks + " tasks in the list");
            storage.saveTasks(taskList.getTasks());
        }
    }
}