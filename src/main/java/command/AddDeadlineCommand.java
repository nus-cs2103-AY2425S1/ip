package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.Deadline;
import task.TaskList;

/**
 * Represents a command to add a deadline task to the task list.
 * It parses the user input to extract the task description and due date.
 */
public class AddDeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Creates an AddDeadlineCommand with the specified arguments.
     * Parses the arguments to extract the task description and due date.
     *
     * @param arguments The string containing the task description and the due date (format: description /by dueDate).
     * @throws KukiShinobuException if the arguments are missing the description or the /by flag and due date.
     */
    public AddDeadlineCommand(String arguments) throws KukiShinobuException {
        String[] parts = arguments.split(" /by ", 2);

        // Checks for missing arguments
        if (parts.length != 2) {
            if (!arguments.contains("/by")) {
                throw new KukiShinobuException("You're missing the /by flag and argument!");
            } else {
                throw new KukiShinobuException("Deadline is missing the description!");
            }
        }

        String taskDescription = parts[0];
        String by = parts[1];
        this.deadline = new Deadline(taskDescription, by);
    }

    /**
     * Executes the command by adding the deadline task to the task list.
     *
     * @param taskList The TaskList where the deadline task will be added.
     * @param storage  The Storage instance to save the updated task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.addTask(this.deadline);
    }
}
