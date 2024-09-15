package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.Event;
import task.TaskList;

/**
 * Represents a command to add an event task to the task list.
 * It parses the user input to extract the task description, start time, and end time.
 */
public class AddEventCommand extends Command {
    private final Event event;

    /**
     * Creates an AddEventCommand with the specified arguments.
     * Parses the arguments to extract the task description, start, and end time.
     *
     * @param arguments The string containing the task description, start time, and end time
     *                  (format: description /from start /to end).
     * @throws KukiShinobuException if the arguments are missing the description, start time, or end time.
     */
    public AddEventCommand(String arguments) throws KukiShinobuException {
        //TODO: Check for missing desc, /from or /to
        //TODO: Modify the logic to split based on "/" instead to accommodate flipped order of flags
//        String[] parts = arguments.split("/", 3);


        String[] parts = arguments.split("\\s+/from\\s+|\\s+/to\\s+", 3);
        if (parts.length != 3) {
            throw new KukiShinobuException("Event is missing description, from or to.");
        }

        String taskDescription = parts[0];
        String start = parts[1];
        String end = parts[2];
        this.event = new Event(taskDescription, start, end);
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
