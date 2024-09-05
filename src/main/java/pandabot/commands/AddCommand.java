package pandabot.commands;

import java.io.IOException;
import java.time.LocalDateTime;

import pandabot.exceptions.InputException;
import pandabot.storage.TaskList;
import pandabot.tasks.Deadline;
import pandabot.tasks.Task;
import pandabot.storage.Storage;
import pandabot.tasks.Event;
import pandabot.tasks.ToDo;
import pandabot.ui.Ui;

/**
 * Represents a command to add a new task to the task list.
 * This command handles the creation of ToDo, Deadline, and Event tasks and adds them to the task list.
 */
public class AddCommand extends Command {
    private final String taskType;
    private final String taskDescription;

    /**
     * Constructs an AddCommand with the specified task type and description.
     *
     * @param taskType the type of task to be added (e.g., "todo", "deadline", "event").
     * @param taskDescription the description of the task to be added.
     */
    public AddCommand(String taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InputException {
        Task task = switch (taskType) {
            case "todo" -> new ToDo("").createTask(taskDescription);
            case "deadline" -> new Deadline("", LocalDateTime.now()).createTask(taskDescription);
            case "event" -> new Event("", LocalDateTime.now(), LocalDateTime.now()).createTask(taskDescription);
            default -> throw new InputException("Invalid task type.");
        };

        tasks.add(task);
        storage.saveTaskList(tasks.getTasks());

        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
