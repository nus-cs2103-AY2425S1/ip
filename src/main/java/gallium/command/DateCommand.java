package gallium.command;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;

import gallium.task.Deadline;
import gallium.task.Event;
import gallium.task.Task;
import gallium.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command that filters and shows tasks by a specific date.
 */
public class DateCommand extends Command {
    private String message;

    /**
     * Constructs a DateCommand with the specified message.
     * 
     * @param message The message containing the date to filter tasks by.
     */
    public DateCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the date command to filter tasks by the date specified in the
     * message.
     * 
     * @param tasklist The list of tasks to execute the command on.
     * @param ui       The user interface that will interact with user.
     * @param storage  The storage that will save any changes made by the command.
     * @throws GalliumException If an error occurs during the execution of the
     *                          command.
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        try {
            LocalDate date = LocalDate.parse(message.split("date ")[1]);
            StringBuilder tasksStringBuilder = new StringBuilder();
            String dateString = "";
            for (int i = 1; i < Task.count; i++) {
                Task task = taskList.getTask(i - 1);
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    dateString = deadline.getDate();
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    dateString = event.getFromDate();
                } else if (task instanceof Todo) {
                    continue;
                }
                if (date.format(DateTimeFormatter.ofPattern("MMM d yyyy")).equals(dateString)) {
                    tasksStringBuilder.append("\n    " + task.toString());
                }
            }
            String tasks = tasksStringBuilder.toString();
            ui.printMatchingDate(tasks);
        } catch (DateTimeParseException e) {
            throw new GalliumException("3:( Invalid date format! Please put in YYYY-MM-DD format!!\nExample: date 2024-09-09");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GalliumException("Please put a space after your command! \nExample: date 2024-09-09");
        }
    }
}
