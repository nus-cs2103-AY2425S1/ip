package command;

import java.util.ArrayList;

import task.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * The ListCommand lists all existing tasks in the task list
 */
public class ListCommand extends UserCommand {
    /**
     * Lists all tasks
     * @param userInput String representing the line that user inputs
     * @param ui Ui to print output
     * @param storage Storage where tasks are saved
     * @param taskList Task list
     */
    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTaskList();

        if (tasks.size() == 0) {
            this.setResponse("No tasks yet...");
        } else {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                res.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
            }
            this.setResponse(res.toString());
        }

        ui.printTasks(tasks);
    }
}