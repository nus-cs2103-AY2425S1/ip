package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.Event;
import task.TaskList;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Represents a command to add an event task to the task list.
 * It parses the user input to extract the task description, start time, and end time.
 */
public class AddEventCommand extends Command {
    private final Event event;

    /**
     * Creates an AddEventCommand with the specified arguments.
     * Parses the arguments to extract the task description, start time, end time, and tags.
     *
     * @param arguments The string containing the task description, start time, end time, and tags
     *                  (format: <name> /from YYYY-MM-DD /to YYYY-MM-DD #another_tag #other_tag).
     * @throws KukiShinobuException if the arguments are missing the description, start time, end time, or tags.
     */
    public AddEventCommand(String arguments) throws KukiShinobuException {
        // Define regex pattern for task description, start and end times, and optional tags
        Pattern pattern = Pattern.compile(
                "^(.*?)\\s+/from\\s+(\\d{4}-\\d{2}-\\d{2})\\s+/to\\s+(\\d{4}-\\d{2}-\\d{2})(?:\\s+(#.*))?$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new KukiShinobuException("Event is missing description, from, to, or tags.");
        }

        String taskDescription = matcher.group(1).trim();
        String start = matcher.group(2).trim();
        String end = matcher.group(3).trim();
        String tagsString = matcher.group(4);

        // Extract tags if present
        Set<String> tags = new HashSet<>();
        if (tagsString != null && !tagsString.trim().isEmpty()) {
            Pattern tagPattern = Pattern.compile("#(\\w+)");
            Matcher tagMatcher = tagPattern.matcher(tagsString);

            while (tagMatcher.find()) {
                tags.add(tagMatcher.group(1));
            }
        }

        this.event = new Event(taskDescription, start, end, tags);
    }

    /**
     * Executes the command by adding the event task to the task list.
     *
     * @param taskList The TaskList where the event task will be added.
     * @param storage  The Storage instance to save the updated task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.addTask(this.event);
    }

}
