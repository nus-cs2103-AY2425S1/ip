package Commands;

import Main.Storage;
import Main.Ui;
import Tasks.TaskList;
import Tasks.Task;

/**
 * Represents a command to show reminders for tasks that are due within a specified number of days.
 */
public class ReminderCommand implements Command {

    private int days;

    /**
     * Constructs a new ReminderCommand with the specified number of days.
     *
     * @param days The number of days within which tasks should be due.
     */
    public ReminderCommand(int days) {
        this.days = days;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList upcomingTasks = new TaskList();  // Create an empty TaskList for upcoming tasks

        for (Task task : tasks.getAll()) {
            if (task.isDueWithinDays(days)) {  // Check if the task is due within the specified number of days
                upcomingTasks.add(task);
            }
        }

        return ui.showUpcomingWeekTasks(upcomingTasks,this.days);
    }
}
