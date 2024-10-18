package opus.commands;

import opus.Storage;
import opus.TaskList;
import opus.exceptions.OpusException;
import opus.Parser;
import opus.tasks.Deadline;
import opus.tasks.Event;
import opus.tasks.Task;
import opus.tasks.ToDo;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand implements Command {
    private final String action;
    private final String details;

    /**
     * Constructs an AddCommand object.
     *
     * @param action The type of task to add.
     * @param details The details of the task to add.
     */
    public AddCommand(String action, String details) {
        this.action = action;
        this.details = details;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws OpusException {
        Task task;
        switch (action) {
        case "todo":
            if (details.isEmpty()) {
                throw new OpusException("The description of a todo cannot be empty.");
            }
            task = new ToDo(details);
            break;
        case "deadline":
            String[] deadlineParts = Parser.parseDeadlineDetails(details);
            task = new Deadline(deadlineParts[0], deadlineParts[1]);
            break;
        case "event":
            String[] eventParts = Parser.parseEventDetails(details);
            task = new Event(eventParts[0], eventParts[1], eventParts[2]);
            break;
        default:
            throw new OpusException("Invalid task type.");
        }
        taskList.addTask(task);
        return "Got it. I've added this task:\n" + task.toString()
                + "\n" + "Now you have " + taskList.getSize() + " tasks in the list.";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
