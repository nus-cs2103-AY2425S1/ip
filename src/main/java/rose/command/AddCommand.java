package rose.command;

import rose.RoseException;
import rose.Storage;
import rose.TaskList;
import rose.Ui;
import rose.task.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private final TaskType taskType;
    private final String taskName;

    public AddCommand(TaskType taskType, String taskName) {
        this.taskType = taskType;
        this.taskName = taskName;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask;
        DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
            ui.showAdd(newTask, tasks.size());

        } catch (DateTimeParseException e) {
            ui.showError("Please enter a valid date in the format yyyy-MM-dd.");
        } catch (RoseException e) {
            ui.showError("OOPS!!! " + e.getMessage());
        }

        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            ui.showError("We cannot save the tasks: " + e.getMessage());
        }
    }

}
