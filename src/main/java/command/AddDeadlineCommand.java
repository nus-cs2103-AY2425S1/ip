package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.Deadline;
import task.TaskList;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {

    private final Deadline deadline;

    /**
     * Constructs an {@code AddDeadlineCommand} with the specified arguments.
     * <p>
     * The arguments should include a description and a deadline in the format:
     * "description /by yyyy-MM-dd". If the arguments are invalid, an exception is thrown.
     * </p>
     *
     * @param arguments The arguments string containing the task description and deadline.
     * @throws KukiShinobuException If the arguments are not correctly formatted.
     */
    public AddDeadlineCommand(String arguments) throws KukiShinobuException {
        // Regex pattern for tags
        Pattern tagPattern = Pattern.compile("#(\\w+)");
        Matcher tagMatcher = tagPattern.matcher(arguments);

        // Extract tags
        HashSet<String> tags = new HashSet<>();
        while (tagMatcher.find()) {
            tags.add(tagMatcher.group(1));
        }

        // Remove tags from arguments
        String argumentsWithoutTags = tagPattern.matcher(arguments).replaceAll("");

        // Regex pattern for the /by argument
        Pattern deadlinePattern = Pattern.compile("^(.*?)/by\\s+(.*)$");
        Matcher deadlineMatcher = deadlinePattern.matcher(argumentsWithoutTags.trim());

        // Check if the pattern matches
        if (!deadlineMatcher.matches()) {
            if (!arguments.contains("/by")) {
                throw new KukiShinobuException("You're missing the /by flag and argument!");
            } else {
                throw new KukiShinobuException("Deadline is missing the description or is in an incorrect format!");
            }
        }

        // Extract task description and due date
        String taskDescription = deadlineMatcher.group(1).trim();
        String dueDateString = deadlineMatcher.group(2).trim();

        // Check if the description is empty
        if (taskDescription.isEmpty()) {
            throw new KukiShinobuException("The description of the deadline cannot be empty!");
        }

        // Validate and parse the due date
        LocalDate dueDate;
        try {
            dueDate = LocalDate.parse(dueDateString);
        } catch (DateTimeParseException e) {
            throw new KukiShinobuException("Deadline date is in an incorrect format! Please use yyyy-MM-dd.");
        }

        // Create Deadline object with extracted tags
        this.deadline = new Deadline(taskDescription, dueDate.toString(), tags);
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
