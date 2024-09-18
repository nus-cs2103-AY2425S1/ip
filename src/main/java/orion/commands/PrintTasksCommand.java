package orion.commands;

import java.util.List;

import orion.utils.Storage;
import orion.utils.TaskList;

/**
 * Represents a command to print the list of tasks.
 * <p>
 * This command retrieves the descriptions of all tasks in the {@link TaskList}
 * and displays them through the user interface.
 * </p>
 */
public class PrintTasksCommand extends Command {

    /**
     * Constructs a {@code PrintTasksCommand}.
     */
    public PrintTasksCommand() {
        super(false);
    }

    /**
     * Executes the command by retrieving the task descriptions from the task list
     * and printing them using the user interface.
     *
     * @param tasks  the {@link TaskList} containing the tasks to be printed
     * @param storage the {@link Storage} for managing the task list (not used in this command)
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<String> taskDescriptions = tasks.getTaskDescriptions();
        if (taskDescriptions.isEmpty()) {
            return "Your task list is empty! Well done!";
        }

        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:");
        for (String s : taskDescriptions) {
            stringBuilder.append("\n");
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
