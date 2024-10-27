package controllers.commands;

import controllers.OutputHandler;
import models.Task;
import models.TaskList;
/**
 * Represents a command to find tasks in the task management system.
 * The {@code FindCommand} class implements the {@code Command} interface and
 * displays a command tasks in the {@code TaskList}.
 *
 * <p>This command retrieves and prints the found tasks.</p>
 */
public class FindCommand implements Command {

    private String taskName;



    /**
     * Constructs a {@code FindCommand} with the specified search string.
     *
     * @param task The name of the task to find .
     */
    public FindCommand(String task) {
        this.taskName = task;
    }

    /**
     * Executes the command to find a task in all tasks in the {@code TaskList}.
     * It prints the found tasks to the console.
     *
     * @param taskList The {@code TaskList} from which tasks are retrieved and displayed.
     */
    @Override
    public void execute(TaskList taskList, OutputHandler outputHandler) {
        assert taskList != null : "taskList must not be null";
        assert outputHandler != null : "outputHandler must not be null";

        outputHandler.print("This are some possible tasks found");

        for (Task task : taskList.getTasks()) {
            if (task.getDescription().contains(this.taskName)) {
                outputHandler.print(task.toString());
            }
        }
    }
}


