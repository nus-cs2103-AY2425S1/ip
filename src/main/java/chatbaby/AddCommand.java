package chatbaby;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task to the task list.
 * This command handles adding ToDo, Deadline, and Event tasks.
 */
public class AddCommand extends Command {
    private static final int VALIDPARTSNUM = 2;
    private int prefixLength;
    private TaskType type;

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

        String description = commandBody.substring(prefixLength).trim();
        if (description.isEmpty()) {
            throw new ChatBabyException("Oh no!!! The description of a "
                    + type.name().toLowerCase() + " cannot be empty.");
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
        case TODO -> newTask = new ToDo(commandBody);
        case DEADLINE -> {
            String[] deadlineParts = commandBody.split("/by ");
            if (deadlineParts.length != VALIDPARTSNUM) {
                throw new ChatBabyException("Oh no!!! The description of this "
                        + type.name().toLowerCase() + " cannot be empty.");
            }
            try {
                String taskDescription = deadlineParts[0].trim();
                String deadline = deadlineParts[1].trim();
                newTask = new Deadline(taskDescription, deadline);
            } catch (DateTimeParseException e) {
                throw new ChatBabyException("Invalid date format. Please use yyyy-MM-dd HH:mm.");
            }
        }
        case EVENT -> {
            String[] eventParts = commandBody.split("/from ");
            if (eventParts.length != VALIDPARTSNUM) {
                throw new ChatBabyException("Oh no!!! The description of this "
                        + type.name().toLowerCase() + " cannot be empty.");
            }
            String name = eventParts[0].trim();
            String[] eventTimes = eventParts[1].split("/to ");
            if (eventTimes.length != VALIDPARTSNUM) {
                throw new ChatBabyException("Oh no!!! The time of this "
                        + type.name().toLowerCase() + " is invalid.");
            }
            try {
                String from = eventTimes[0].trim();
                String to = eventTimes[1].trim();
                newTask = new Event(name, from, to);
            } catch (DateTimeParseException e) {
                throw new ChatBabyException("Invalid date format. Please use yyyy-MM-dd HH:mm.");
            }
        }
        default -> throw new ChatBabyException("Oh no!!! I'm sorry, but I don't understand that command.");
        }
        tasks.addTask(newTask);
        System.out.println("Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }
}
