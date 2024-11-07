package chatbuddy.command;

import chatbuddy.exception.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.task.Deadline;
import chatbuddy.task.Event;
import chatbuddy.task.Task;
import chatbuddy.task.TaskList;
import chatbuddy.task.ToDo;
import chatbuddy.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task (ToDo, Deadline, or Event) to the task list.
 */
public class AddCommand extends Command {

    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private String description;
    private String type;

    /**
     * Constructs an AddCommand with the given description and task type.
     *
     * @param description The task description.
     * @param type        The task type ("T" for ToDo, "D" for Deadline, "E" for Event).
     */
    public AddCommand(String description, String type) {
        assert description != null && !description.trim().isEmpty() : "Description must not be null or empty";
        this.description = description;
        this.type = type;
    }

    /**
     * Executes the command to add a task to the task list.
     * Depending on the type, it will add a ToDo, Deadline, or Event.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage to save the task list.
     * @return The response after adding the task.
     * @throws ChatBuddyException If there are issues with the task description or date formats.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Task task;
        switch (type) {
        case TODO_TYPE:
            task = createToDo();
            break;

        case DEADLINE_TYPE:
            task = createDeadline();
            break;

        case EVENT_TYPE:
            task = createEvent();
            break;

        default:
            throw new ChatBuddyException("Unknown task type.");
        }

        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        ui.showAddTask(task, tasks.size());
        return ui.getOutput();
    }

    /**
     * Creates a ToDo task based on the provided description.
     *
     * @return The created ToDo task.
     * @throws ChatBuddyException If the description is invalid.
     */
    private ToDo createToDo() throws ChatBuddyException {
        if (description == null || description.trim().isEmpty()) {
            throw new ChatBuddyException("The description of a ToDo cannot be empty.");
        }
        return new ToDo(description);
    }

    /**
     * Creates a Deadline task based on the provided description and due date.
     *
     * @return The created Deadline task.
     * @throws ChatBuddyException If the description or date format is invalid.
     */
    private Deadline createDeadline() throws ChatBuddyException {
        String[] deadlineParts = description.split(" /by ");
        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
            throw new ChatBuddyException("The due date of a deadline cannot be empty. Format: deadline <description> /by <date>");
        }
        try {
            return new Deadline(deadlineParts[0], deadlineParts[1]);
        } catch (DateTimeParseException e) {
            throw new ChatBuddyException("Invalid date format for the deadline. Please use 'yyyy-MM-dd'.");
        }
    }

    /**
     * Creates an Event task based on the provided description, start date, and end date.
     *
     * @return The created Event task.
     * @throws ChatBuddyException If the description or date format is invalid.
     */
    private Event createEvent() throws ChatBuddyException {
        String[] eventParts = description.split(" /from ");
        if (eventParts.length < 2) {
            throw new ChatBuddyException("The start time of the event is missing. Format: event <description> /from <start> /to <end>");
        }

        String[] dateParts = eventParts[1].split(" /to ");
        if (dateParts.length < 2) {
            throw new ChatBuddyException("The end time of the event is missing. Format: event <description> /from <start> /to <end>");
        }

        try {
            return new Event(eventParts[0].trim(), dateParts[0].trim(), dateParts[1].trim());
        } catch (DateTimeParseException e) {
            throw new ChatBuddyException("Invalid date format for the event. Please use 'yyyy-MM-dd'.");
        }
    }

}
