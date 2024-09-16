package commands;

import java.util.ArrayList;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to find and list tasks containing a specific word in their description.
 * This command filters the tasks based on the given keyword and displays the matching tasks to the console.
 */
public class FindCommand extends Command {

    private String word;

    /**
     * Constructs a {@code FindCommand} with the specified keyword to search for in the task descriptions.
     *
     * @param word The keyword to search for in the task descriptions.
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Returns whether this command will exit the application.
     * For the FindCommand, this method returns false because finding tasks does not terminate the application.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to find and list tasks that contain the specified keyword.
     * It retrieves the tasks from the {@code TaskList}, filters them based on the keyword,
     * and prints the matching tasks to the console.
     * It also prints a header message to indicate that the filtered task list is being displayed.
     *
     * @param tasks  The {@code TaskList} containing the tasks to be filtered and listed.
     * @param ui     The {@code Ui} object used for interaction with the user (not used in this command but
     *         required by the method signature).
     * @param storage The {@code Storage} object used for persisting tasks (not used in this command but
     *         required by the method signature).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the filtered tasks:\n");
        ArrayList<Task> list = tasks.getTasks();
        int len = list.size();
        for (int i = 0; i < len; i++) {
            if (list.get(i).toString().contains(word)) {
                String item = i + 1 + "." + list.get(i) + "\n";
                output.append(item);
            }
        }
        return output.toString();
    }
}
