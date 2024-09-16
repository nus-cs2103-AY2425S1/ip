package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.Deadline;
import task.TaskList;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Represents a command to add a deadline task to the task list.
 * It parses the user input to extract the task description and due date.
 */
public class AddDeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Creates an AddDeadlineCommand with the specified arguments.
     * Parses the arguments to extract the task description, due date, and tags.
     *
     * @param arguments The string containing the task description, due date (format: description /by dueDate), and optional tags (format: #tag).
     * @throws KukiShinobuException if the arguments are missing the description or the /by flag and due date.
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
        Pattern deadlinePattern = Pattern.compile("^(.*?)/by\\s+(\\d{4}-\\d{2}-\\d{2})$");
        Matcher deadlineMatcher = deadlinePattern.matcher(argumentsWithoutTags.trim());

        // Check if the pattern matches
        if (!deadlineMatcher.matches()) {
            if (!arguments.contains("/by")) {
                throw new KukiShinobuException("You're missing the /by flag and argument!");
            } else {
                throw new KukiShinobuException("Deadline is missing the description!");
            }
        }

        // Extract task description and due date
        String taskDescription = deadlineMatcher.group(1).trim();
        String dueDate = deadlineMatcher.group(2).trim();

        // Create Deadline object with extracted tags
        this.deadline = new Deadline(taskDescription, dueDate, tags);
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
