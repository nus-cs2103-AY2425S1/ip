package GPT;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks in the GPT application based on a keyword.
 * The command searches through the task list and displays matching tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand by extracting the keyword from the user input.
     *
     * @param input The user input string containing the command to find tasks.
     */
    public FindCommand(String input) {
        this.keyword = input.substring(5).trim();  // Extract keyword after "find "
    }

    /**
     * Executes the command to find tasks that match the keyword.
     * Displays the matching tasks or a message if no tasks are found.
     *
     * @param taskList The list of tasks to search.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }

        ui.showMatchingTasks(matchedTasks);
    }
}
