package bottleopener.command;

import bottleopener.task.Deadline;
import bottleopener.task.Event;
import bottleopener.task.Task;
import bottleopener.task.Tasklist;
import bottleopener.task.ToDo;
import bottleopener.ui.Ui;

/**
 * The {@code AddCommand} class handles the creation and addition of tasks
 * (ToDo, Deadline, Event) to the task list in the BottleOpener chatbot.
 * It processes the task type and its associated information to create the appropriate task.
 */
public class AddCommand implements Command {
    private Tasklist tasklist;
    private final String taskType;
    private final String taskInformation;

    /**
     * Constructs an {@code AddCommand} with the specified task list, task type, and task information.
     *
     * @param tasklist The list to which the new task will be added.
     * @param taskType The type of the task ("todo", "deadline", "event").
     * @param taskInformation The description and other details of the task.
     */
    public AddCommand(Tasklist tasklist, String taskType, String taskInformation) {
        this.tasklist = tasklist;
        this.taskType = taskType;
        this.taskInformation = taskInformation;
    }

    /**
     * Executes the {@code AddCommand} by creating the appropriate task (ToDo, Deadline, or Event)
     * and adding it to the task list.
     *
     * @return A string confirming that the task was added, or an error message if there was an issue.
     */
    @Override
    public String runCommand() {
        try {
            Task newTask = createTask();
            this.tasklist.addTask(newTask);
            return Ui.wrapSpacer(String.format("added: %s%n", newTask));
        } catch (ArrayIndexOutOfBoundsException e) {
            return Ui.showCommandFormatError();
        } catch (IllegalArgumentException e) {
            return Ui.showInvalidDateFormatError();
        }
    }

    /**
     * Creates the appropriate task (ToDo, Deadline, or Event) based on the task type.
     *
     * @return The created task.
     * @throws ArrayIndexOutOfBoundsException If the task information is improperly formatted.
     * @throws IllegalArgumentException If the task type is invalid.
     */
    private Task createTask() throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        switch (taskType) {
        case "todo":
            return new ToDo(taskInformation);
        case "deadline":
            return createDeadline(taskInformation);
        case "event":
            return createEvent(taskInformation);
        default:
            throw new IllegalArgumentException("Invalid task type");
        }
    }

    /**
     * Creates a {@code Deadline} task using the provided task information.
     *
     * @param info The task information containing the action and due date.
     * @return The created {@code Deadline} task.
     * @throws ArrayIndexOutOfBoundsException If the task information is improperly formatted.
     * @throws IllegalArgumentException If the due date format is invalid.
     */
    private Task createDeadline(String info) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        String[] activity = info.split(" /by ", 2);
        String action = activity[0].trim();
        String due = activity[1].trim();
        return new Deadline(action, due);
    }

    /**
     * Creates an {@code Event} task using the provided task information.
     *
     * @param info The task information containing the action, start, and end times.
     * @return The created {@code Event} task.
     * @throws ArrayIndexOutOfBoundsException If the task information is improperly formatted.
     * @throws IllegalArgumentException If the date format is invalid.
     */
    private Task createEvent(String info) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        String[] activity = info.split(" /from | /to ", 3);
        String action = activity[0].trim();
        String start = activity[1].trim();
        String end = activity[2].trim();
        return new Event(action, start, end);
    }
}
