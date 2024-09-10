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
        Task newTask;
        final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            switch (taskType) {
            case TODO:
                newTask = new Todo(taskName);
                break;
            case DEADLINE:
                String[] deadlineParts = taskName.split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new RoseException("Deadline task is missing '/by'.");
                }
                newTask = new Deadline(deadlineParts[0], LocalDate.parse(deadlineParts[1], INPUT_FORMAT));
                break;
            case EVENT:
                String[] eventPartsA = taskName.split(" /from ");
                if (eventPartsA.length < 2) {
                    throw new RoseException("Event task is missing '/from'.");
                }
                String[] eventPartsB = eventPartsA[1].split(" /to ");
                if (eventPartsB.length < 2) {
                    throw new RoseException("Event task is missing '/to'.");
                }
                newTask = new Event(eventPartsA[0],
                        LocalDate.parse(eventPartsB[0], INPUT_FORMAT),
                        LocalDate.parse(eventPartsB[1], INPUT_FORMAT));
                break;
            default:
                throw new RoseException("Unknown task type.");
            }

            tasks.addTask(newTask);
            try {
                storage.save(tasks.getTaskList());
            } catch (IOException e) {
                ui.showError("We cannot save the tasks: " + e.getMessage());
            }
            return ui.showAdd(newTask, tasks.size());
        } catch (DateTimeParseException e) {
            return ui.showError("Please enter a valid date in the format yyyy-MM-dd.");
        } catch (RoseException e) {
            return ui.showError("OOPS!!! " + e.getMessage());
        }
    }
}
