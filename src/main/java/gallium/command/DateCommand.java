package gallium.command;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;

import gallium.task.Deadline;
import gallium.task.Event;
import gallium.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command that filters and shows tasks by a specific date.
 */
public class DateCommand extends Command {
    private String Message;

    /**
     * Constructs a DateCommand with the specified message.
     * 
     * @param message The message containing the date to filter tasks by.
     */
    public DateCommand(String Message) {
        this.Message = Message;
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
            LocalDate date = LocalDate.parse(Message.split("date ")[1]);
            StringBuilder tasksStringBuilder = new StringBuilder();
            for (int i = 1; i < Task.count; i++) {
                Task task = taskList.getTask(i - 1);
                if (task.getDesc().startsWith("[D]") || task.getDesc().startsWith("deadline ")) {
                    Deadline deadline = (Deadline) task;
                    if (date.format(DateTimeFormatter.ofPattern("MMM d yyyy")).equals(deadline.getDate())) {
                        tasksStringBuilder.append("\n    " + deadline.toString());
                    }
                } else if (task.getDesc().startsWith("[E]") || task.getDesc().startsWith("event ")) {
                    Event event = (Event) task;
                    if (date.format(DateTimeFormatter.ofPattern("MMM d yyyy")).equals(event.getToDate())) {
                        tasksStringBuilder.append("\n    " + event.toString());
                    }
                }
            }
            String tasks = tasksStringBuilder.toString();
            ui.printMatchingDate(tasks);
        } catch (DateTimeParseException e) {
            ui.showWrongDateTimeFormat();
        }
    }
}
