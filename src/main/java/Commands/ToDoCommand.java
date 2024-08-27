package Commands;

import Main.TaskList;
import UI.Ui;
import Tasks.Task;
import Tasks.ToDo;
import Storage.Storage;

public class ToDoCommand extends Command {
    private final String taskDescription;

    public ToDoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskDescription == null || this.taskDescription.trim().isEmpty()) {
            ui.showError("todo WHAT????");
        } else {
            Task newTask = new ToDo(this.taskDescription);
            taskList.addTask(newTask);
            int numTasks = taskList.size();

            ui.showMessage("Got it. I've added this task:");
            ui.showMessage("  " + newTask.toString());
            ui.showMessage("Now you have " + numTasks + " tasks in the list");
            storage.saveTasks(taskList.getTasks());
        }
    }
}
