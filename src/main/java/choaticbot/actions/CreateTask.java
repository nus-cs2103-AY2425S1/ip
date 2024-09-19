package choaticbot.actions;

import choaticbot.exceptions.ChoaticBotException;
import choaticbot.exceptions.WrongInputFormatException;
import choaticbot.tasks.Deadlines;
import choaticbot.tasks.Events;
import choaticbot.tasks.Task;
import choaticbot.tasks.TaskList;
import choaticbot.tasks.ToDos;

/**
 * The {@code CreateTask} class represents an action that creates a new task
 * (To-Do, Deadline, or Event) and adds it to the task list.
 */
public class CreateTask extends Action {

    /**
     * The type of task to be created (e.g., "todo", "deadline", "event").
     */
    private String taskType;

    /**
     * The details of the task, including the task name and additional information
     * (such as deadlines or event times).
     */
    private String details;

    /**
     * Constructs a {@code CreateTask} action with the specified task type and details.
     *
     * @param taskList The task list where the new task will be added.
     * @param taskType The type of task to create ("todo", "deadline", or "event").
     * @param details The details required for the task, such as the task description or time information.
     */
    public CreateTask(TaskList taskList, String taskType, String details) {
        super(taskList);
        this.taskType = taskType;
        this.details = details;
    }

    /**
     * Executes the action of creating a new task based on the task type and details.
     * The task is added to the task list.
     * <p>
     * - If the task is a "todo", it creates a {@link ToDos}.
     * - If the task is a "deadline", it creates a {@link Deadlines} with the deadline details.
     * - If the task is an "event", it creates an {@link Events} with the event's start and end times.
     *
     * @return an {@link ActionResult} containing the result of adding the task to the task list
     * @throws ChoaticBotException if the task type is unknown or the input format is incorrect
     */
    @Override
    public ActionResult execute() throws ChoaticBotException {
        Task task = switch (this.taskType) {
        case "todo" -> new ToDos(this.details);
        case "deadline" -> {
            //[0] = taskName, [1] = deadline
            String[] deadlineDetails = this.details.split("/by ");
            if (deadlineDetails.length != 2) {
                throw new WrongInputFormatException("Expected format example: Read a book /by 2024-01-31 18:00");
            }
            yield new Deadlines(deadlineDetails[0], deadlineDetails[1]);
        }
        case "event" -> {
            //[0] = taskName, [1] = from, [2] = to
            String[] eventDetails = this.details.split("/");
            if (eventDetails.length != 3) {
                throw new WrongInputFormatException("Expected format example: Buy a kite event "
                        + "/from Monday /to Saturday");
            }
            yield new Events(eventDetails[0], eventDetails[1], eventDetails[2]);
        }
        default -> null;
        };

        if (task != null) {
            return this.taskList.addTask(task);
        } else {
            throw new ChoaticBotException("Unknown Task");
        }
    }
}
