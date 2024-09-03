package command;

import java.util.ArrayList;

import exception.InvalidArgumentException;
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
            ui.printException(new InvalidArgumentException("find", keyword));
            return;
        }

        ArrayList<Task> tasks = taskList.filterByKeyword(keyword);
        ui.printMatchingTasks(tasks);
    }
}