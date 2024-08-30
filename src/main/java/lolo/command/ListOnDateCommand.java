package lolo.command;

import lolo.Ui;
import lolo.storage.Storage;
import lolo.task.TaskList;

import java.time.LocalDateTime;

/**
 * Represents a command to list all tasks occurring on a specific date.
 * This command interacts with the user interface to display tasks
 * that are scheduled for the given date.
 */
class ListOnDateCommand extends Command {
    private LocalDateTime date;

    /**
     * Constructs a ListOnDateCommand with the specified date.
     *
     * @param date The date for which tasks should be listed.
     */
    public ListOnDateCommand(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Executes the command by retrieving and displaying the tasks scheduled
     * for the specified date.
     * This command does not modify the task list or storage.
     *
     * @param tasks The list of tasks to be filtered by date.
     * @param ui The user interface to show the tasks on the specified date.
     * @param storage The storage, which is not used by this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList tasksOnDate = tasks.getTasksOnDate(date);
        ui.showTasksOnDate(date.toLocalDate().toString(), tasksOnDate);
    }
}
