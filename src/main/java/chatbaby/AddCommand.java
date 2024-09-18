package chatbaby;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task to the task list.
 * This command handles adding ToDo, Deadline, and Event tasks.
 */
public class AddCommand extends Command {
    private static final int VALID_PARTS_NUM = 2;
    private int prefixLength;
    private final TaskType type;

    /**
     * Constructs an AddCommand with the given command body and task type.
     *
     * @param commandBody The body of the command entered by the user.
     * @param type The type of task (ToDo, Deadline, Event).
     * @throws ChatBabyException If the task description is empty or invalid.
     */
    public AddCommand(String commandBody, TaskType type) throws ChatBabyException {
        super(commandBody);
        this.type = type;
        switch (type) {
        case TODO -> prefixLength = 5;
        case DEADLINE -> prefixLength = 9;
        case EVENT -> prefixLength = 6;
        default -> {
            break;
        }
        }
    }

    /**
     * Executes the AddCommand by adding the corresponding task (ToDo, Deadline, or Event)
     * to the task list.
     *
     * @param tasks The task list where the new task will be added.
     * @param ui The UI object to handle user interactions.
     * @param storage The storage object to handle saving the task to a file.
     * @throws ChatBabyException If the task description is empty or invalid, or if the date format is incorrect.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException {
        Task newTask;
        switch (type) {
        case TODO -> newTask = createToDo();
        case DEADLINE -> newTask = createDeadline();
        case EVENT -> newTask = createEvent();
        default -> throw new ChatBabyException("Oh no!!! I'm sorry, but I don't understand that command.");
        }
        tasks.addTask(newTask);
        System.out.println("Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Creates a ToDo task from the command body.
     *
     * @return The newly created ToDo task.
     * @throws ChatBabyException If the description is empty.
     */
    private ToDo createToDo() throws ChatBabyException {
        if (checkEmptyDescription(commandBody)) {
            throw new ChatBabyException("Oh no!!! The description of this "
                    + type.name().toLowerCase() + " cannot be empty.");
        }
        return new ToDo(commandBody);
    }

    /**
     * Creates a Deadline task from the command body.
     *
     * @return The newly created Deadline task.
     * @throws ChatBabyException If the description or date format is invalid.
     */
    private Deadline createDeadline() throws ChatBabyException {
        if (checkEmptyDescription(commandBody)) {
            throw new ChatBabyException("Oh no!!! The description of this "
                    + type.name().toLowerCase() + " cannot be empty.");
        }
        String[] deadlineParts = commandBody.split("/by ");
        if (deadlineParts.length != VALID_PARTS_NUM) {
            throw new ChatBabyException("Oh no!!! The description of this "
                    + type.name().toLowerCase() + " is invalid.");
        }
        try {
            String taskDescription = deadlineParts[0].trim();
            String deadline = deadlineParts[1].trim();
            return new Deadline(taskDescription, deadline);
        } catch (DateTimeParseException e) {
            throw new ChatBabyException("Invalid date format. Please use yyyy-MM-dd HH:mm.");
        }
    }

    /**
     * Creates an Event task from the command body.
     *
     * @return The newly created Event task.
     * @throws ChatBabyException If the description or time format is invalid.
     */
    private Event createEvent() throws ChatBabyException {
        if (checkEmptyDescription(commandBody)) {
            throw new ChatBabyException("Oh no!!! The description of this "
                    + type.name().toLowerCase() + " cannot be empty.");
        }
        String[] eventParts = commandBody.split("/from ");
        if (eventParts.length != VALID_PARTS_NUM) {
            throw new ChatBabyException("Oh no!!! The description of this "
                    + type.name().toLowerCase() + " is invalid.");
        }
        String name = eventParts[0].trim();
        String[] eventTimes = eventParts[1].split("/to ");
        if (eventTimes.length != VALID_PARTS_NUM) {
            throw new ChatBabyException("Oh no!!! The time of this "
                    + type.name().toLowerCase() + " is invalid.");
        }
        try {
            String from = eventTimes[0].trim();
            String to = eventTimes[1].trim();
            return new Event(name, from, to);
        } catch (DateTimeParseException e) {
            throw new ChatBabyException("Invalid date format. Please use yyyy-MM-dd HH:mm.");
        }
    }

    private boolean checkEmptyDescription(String commandBody) {
        String description = commandBody.substring(prefixLength).trim();
        return description.length() <= prefixLength;
    }
}
