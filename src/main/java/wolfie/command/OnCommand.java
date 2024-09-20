package wolfie.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import wolfie.exception.WolfieException;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to list all tasks on a specific date.
 */
public class OnCommand extends Command {
    private final LocalDate date;

    /**
     * Constructs an OnCommand object to list all tasks on a specific date.
     *
     * @param arguments the date to list tasks on
     */
    public OnCommand(String arguments) throws WolfieException {
        try {
            this.date = LocalDate.parse(arguments.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new WolfieException("⚠ Please enter the date in the format:\n"
                    + "yyyy-MM-dd.");
        }
    }

    /**
     * Executes the command to list all tasks on a specific date.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage
     * @return the response to the user
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> tasksOnDate = tasks.getTasksOnDate(date);
        Task[] tasksArray = tasksOnDate.toArray(new Task[0]);
        return ui.showTasksOnDate(date, tasksArray);
    }
}
