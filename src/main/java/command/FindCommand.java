package command;

import java.util.ArrayList;

import exception.InvalidArgumentException;
import exception.LevelHundredException;
import task.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * The FindCommand class finds tasks with matching keyword
 */
public class FindCommand extends UserCommand {
    /**
     * Finds tasks in the task list that matches the keyword
     *
     * @param userInput String representing the line that user inputs
     * @param ui Ui to print output
     * @param storage Storage where tasks are saved
     * @param taskList Task list
     */
    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) {
        int keywordIdx = userInput.indexOf(" ");
        String keyword = userInput.substring(keywordIdx + 1);
        if (keyword.isEmpty()) {
            LevelHundredException e = new InvalidArgumentException("find", keyword);
            this.setResponse(e.toString());
            ui.printException(e);
            return;
        }
        assert (keyword != "") : "Keyword should not be empty";

        ArrayList<Task> tasks = taskList.filterByKeyword(keyword);

        if (tasks.isEmpty()) {
            this.setResponse("No matching tasks found...");
        } else {
            StringBuilder res = new StringBuilder("Here are the matching tasks in your list\n");
            for (int i = 0; i < tasks.size(); i++) {
                res.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
            }
            this.setResponse(res.toString());
        }

        ui.printMatchingTasks(tasks);
    }
}