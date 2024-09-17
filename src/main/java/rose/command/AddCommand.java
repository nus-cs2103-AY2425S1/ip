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
     * Adds a new task to the list.
     *
     * @param tasks current list of tasks.
     * @param ui ui object to show message to user.
     * @param storage storage object to store the data.
     * @return A message indicating the result of the operation, such as the added task or an error message.
     * @throws RoseException If input is incomplete or tasktype is unknown.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = createTask(taskType, taskName);
            tasks.addTask(newTask);
            saveTasks(storage, tasks, ui);
            return ui.showAdd(newTask, tasks.size());
        } catch (DateTimeParseException e) {
            return ui.showError("Please enter a valid date in the format yyyy-MM-dd.");
        } catch (RoseException e) {
            return ui.showError("OOPS!!! " + e.getMessage());
        }
    }

    private Task createTask(TaskType taskType, String taskName) throws RoseException, DateTimeParseException {
        switch (taskType) {
        case TODO:
            return createTodoTask(taskName);
        case DEADLINE:
            return createDeadlineTask(taskName);
        case EVENT:
            return createEventTask(taskName);
        default:
            throw new RoseException("Unknown task type.");
        }
    }

    private Task createTodoTask(String taskName) {
        return new Todo(taskName);
    }

    private Task createDeadlineTask(String taskName) throws RoseException {
        String[] parts = taskName.split(DEADLINE_DELIMITER);
        if (parts.length < 2) {
            throw new RoseException("Deadline task is missing '" + DEADLINE_DELIMITER + "'.");
        }
        return new Deadline(parts[0], LocalDate.parse(parts[1], INPUT_FORMAT));
    }

    private Task createEventTask(String taskName) throws RoseException {
        String[] partsA = taskName.split(EVENT_FROM_DELIMITER);
        if (partsA.length < 2) {
            throw new RoseException("Event task is missing '" + EVENT_FROM_DELIMITER + "'.");
        }
        String[] partsB = partsA[1].split(EVENT_TO_DELIMITER);
        if (partsB.length < 2) {
            throw new RoseException("Event task is missing '" + EVENT_TO_DELIMITER + "'.");
        }
        return new Event(partsA[0], LocalDate.parse(partsB[0], INPUT_FORMAT),
                LocalDate.parse(partsB[1], INPUT_FORMAT));
    }

    private void saveTasks(Storage storage, TaskList tasks, Ui ui) {
        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            ui.showError("We cannot save the tasks: " + e.getMessage());
        }
    }
}
