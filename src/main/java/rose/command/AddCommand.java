package rose.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import rose.RoseException;
import rose.Storage;
import rose.TaskList;
import rose.Ui;
import rose.task.Deadline;
import rose.task.Event;
import rose.task.Task;
import rose.task.TaskType;
import rose.task.Todo;

/**
 * Represents a command used by user to add task to the list.
 * <p>An <code>AddCommand</code> object is represented by its task type and description of the task.
 */
public class AddCommand extends Command {
    private static final String DEADLINE_DELIMITER = " /by ";
    private static final String EVENT_FROM_DELIMITER = " /from ";
    private static final String EVENT_TO_DELIMITER = " /to ";
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final TaskType taskType;
    private final String taskName;


    public AddCommand(TaskType taskType, String taskName) {
        this.taskType = taskType;
        this.taskName = taskName;
    }

    /**
     * Executes the add task command.
     * <p>
     * Adds a new task to the task list based on the task type and saves it to storage.
     *
     * @param tasks The current list of tasks.
     * @param ui The {@code Ui} object to show messages to the user.
     * @param storage The {@code Storage} object to save the task list.
     * @return A message indicating the added task or an error message.
     * @throws RoseException If the input is incomplete or the task type is unknown.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = createTask(taskType, taskName);
            assert newTask != null : "Task added cannot be null.";
            tasks.addTask(newTask);
            saveTasks(storage, tasks, ui);
            return ui.showAdd(newTask, tasks.size());
        } catch (DateTimeParseException e) {
            return ui.showError("Please enter a valid date in the format yyyy-MM-dd.");
        } catch (RoseException e) {
            return ui.showError("OOPS!!! " + e.getMessage());
        }
    }

    /**
     * Creates a task based on the provided task type and name.
     *
     * @param taskType The type of task to create (Todo, Deadline, Event).
     * @param taskName The name or description of the task.
     * @return The created {@code Task}.
     * @throws RoseException If the task type is unknown or the input format is invalid.
     * @throws DateTimeParseException If the date format is incorrect.
     */
    private Task createTask(TaskType taskType, String taskName) throws RoseException, DateTimeParseException {
        assert taskName != null : "Task name cannot be null.";
        String tag = "";
        String taskDesc = taskName;
        if (taskName.contains("#")) {
            String[] parts = taskName.split("#");
            tag = parts[1].trim();
            taskDesc = parts[0].trim();
        }

        switch (taskType) {
        case TODO:
            return createTodoTask(taskDesc, tag);
        case DEADLINE:
            return createDeadlineTask(taskDesc, tag);
        case EVENT:
            return createEventTask(taskDesc, tag);
        default:
            throw new RoseException("Unknown task type.");
        }
    }

    /**
     * Creates a Todo task.
     *
     * @param taskDesc The description of the task.
     * @param tag The tag associated with the task (if any).
     * @return The created {@code Todo} task.
     */
    private Task createTodoTask(String taskDesc, String tag) {
        return new Todo(taskDesc, tag);
    }

    /**
     * Creates a Deadline task.
     *
     * @param taskDesc The description of the task, including the deadline date.
     * @param tag The tag associated with the task (if any).
     * @return The created {@code Deadline} task.
     * @throws RoseException If the deadline date is missing or incorrectly formatted.
     */
    private Task createDeadlineTask(String taskDesc, String tag) throws RoseException {
        String[] parts = taskDesc.split(DEADLINE_DELIMITER);
        if (parts.length < 2) {
            throw new RoseException("Deadline task is missing '" + DEADLINE_DELIMITER + "'.");
        }
        return new Deadline(parts[0].trim(), LocalDate.parse(parts[1].trim(), INPUT_FORMAT), tag);
    }

    /**
     * Creates an Event task.
     *
     * @param taskDesc The description of the task, including the start and end dates.
     * @param tag The tag associated with the task (if any).
     * @return The created {@code Event} task.
     * @throws RoseException If the event dates are missing or incorrectly formatted.
     */
    private Task createEventTask(String taskDesc, String tag) throws RoseException {
        String[] partsA = taskDesc.split(EVENT_FROM_DELIMITER);
        if (partsA.length < 2) {
            throw new RoseException("Event task is missing '" + EVENT_FROM_DELIMITER + "'.");
        }
        String[] partsB = partsA[1].split(EVENT_TO_DELIMITER);
        if (partsB.length < 2) {
            throw new RoseException("Event task is missing '" + EVENT_TO_DELIMITER + "'.");
        }
        return new Event(partsA[0].trim(), LocalDate.parse(partsB[0].trim(), INPUT_FORMAT),
                LocalDate.parse(partsB[1].trim(), INPUT_FORMAT), tag);
    }

    /**
     * Saves the list of tasks to storage.
     *
     * @param storage The {@code Storage} object used to save tasks.
     * @param tasks The current list of tasks to save.
     * @param ui The {@code Ui} object to show error messages if saving fails.
     */
    private void saveTasks(Storage storage, TaskList tasks, Ui ui) {
        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            ui.showError("We cannot save the tasks: " + e.getMessage());
        }
    }
}
