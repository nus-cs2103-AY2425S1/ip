package dook.commands;


import java.io.IOException;
import java.util.ArrayList;

import dook.Dook;
import dook.DookException;
import dook.storage.Storage;
import dook.tasks.Task;
import dook.tasks.TaskList;
import dook.ui.Ui;

/**
 * The command to find a Task by searching for a keyword.
 */
public class FindCommand extends Command {

    private String keyword;
    private String foundWord = "Here are the matching tasks in your list: ";

    /**
     * Creates a FindCommand to search for tasks containing the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand, searching for tasks that contain the specified keyword.
     * Displays the matching tasks or throws an exception if no tasks are found.
     *
     * @param taskList The TaskList from which tasks will be searched.
     * @param ui The Ui object that handles user interactions and displays results.
     * @param storage The Storage object that handles saving the TaskList (not used in this command).
     * @return A message listing the tasks that match the keyword, or an exception message if no tasks match.
     * @throws DookException If the keyword is empty or no tasks contain the keyword.
     * @throws IOException If an I/O error occurs while interacting with the UI (not expected in this command).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DookException, IOException {
        exitIfNoKeyword();

        TaskList matches = new TaskList(new ArrayList<Task>());

        for (int i = 0; i < taskList.numOfTasks(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(this.keyword)) {
                matches.add(task);
            }
        }

        return printMessages(matches, ui);
    }

    private void exitIfNoKeyword() throws DookException {
        if (this.keyword.isEmpty()) {
            throw new DookException("Provide a keyword");
        }
    }

    private void exitIfNoMatches(TaskList matches) throws DookException {
        if (matches.isEmpty()) {
            throw new DookException("No tasks have the following keyword: \"" + this.keyword + "\"");
        }
    }

    private String printMessages(TaskList matches, Ui ui) throws DookException {
        ui.separate();
        String message = foundWord;

        ui.showMessage(message);
        for (int i = 0; i < matches.numOfTasks(); i++) {
            ui.showMessage((i + 1) + ". " + matches.getTask(i));
            message = message.concat("\n" + (i + 1) + ". " + matches.getTask(i));
        }

        ui.separate();
        return message;
    }
}
