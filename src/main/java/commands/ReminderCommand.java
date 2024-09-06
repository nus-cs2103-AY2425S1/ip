package commands;

import tasks.Task;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;


/**
 * Command to show reminders for tasks due within a specified number of days.
 */
public class ReminderCommand implements Command {
    private final int days;

    /**
     * Constructs a new ReminderCommand with the specified number of days.
     *
     * @param days Number of days within which tasks should be due.
     */
    public ReminderCommand(int days) {
        this.days = days;
    }

    /**
     * Executes the command to show tasks due within a specified number of days.
     *
     * @param tasks   TaskList to search within.
     * @param ui      UI to handle user interaction.
     * @param storage Storage (not used in this command).
     * @return Result message showing upcoming tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList upcomingTasks = new TaskList();

        for (Task task : tasks.getAll()) {
            if (task.isDueWithinDays(days)) {
                upcomingTasks.add(task);
            }
        }

        return ui.showUpcomingWeekTasks(upcomingTasks, days);
    }
}
