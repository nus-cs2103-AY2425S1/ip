package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Task;
import yapbot.tasks.ToDo;
import yapbot.util.Storage;
import yapbot.util.TaskList;

/**
 * Command for creation of ToDo tasks.
 */
public class ToDoCommand extends Command {
    private String taskDetails;

    /**
     * Creates a ToDoCommand instance.
     *
     * @param taskDetails Details of the ToDo task to be created.
     */
    public ToDoCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     * {@inheritDoc}
     * Creates and adds the ToDo Task to TaskList.
     *
     * @throws YapBotException if task details is empty.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YapBotException {
        Task task = new ToDo(taskDetails);
        tasks.addTask(task);

        String successMessage = "Adding Task...\nSuccess\nTask added to database:\n" + "  "
                + task + "\n" + "Total tasks: " + tasks.size();

        return successMessage;
    }

}
