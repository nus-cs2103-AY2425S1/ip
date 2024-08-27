package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.Task;
import friendlybot.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * DateCommand is a Command that prints a list of tasks that happen on a given date.
 */
public class DateCommand extends Command {
    private LocalDate date;

    /**
     * A constructor for DateCommand.
     *
     * @param date Date to scan for tasks that happen on that date.
     */
    public DateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Prints a list of tasks that happen on the given date upon execution.
     *
     * @param tasks An instance of TaskList where the new task is added to.
     * @param ui An instance of Ui (User Interface) that handles the interactions between FriendlyBot and user.
     * @param storage An instance of Storage that loads tasks and saves tasks in a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTasksOnDate(date);
        for (Task task : taskList) {
            Ui.print(task.toString());
        }
    }
}
