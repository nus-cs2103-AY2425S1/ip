package lolo.command;

import lolo.storage.Storage;
import lolo.task.TaskList;

import java.time.LocalDateTime;

/**
 * Represents a command to list all tasks occurring on a specific date.
 * This command returns the tasks scheduled for the given date as a string
 * to be displayed in the UI.
 */
public class ListOnDateCommand extends Command {
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
     * Executes the command by retrieving and returning the tasks scheduled
     * for the specified date as a string. This command does not modify the task list or storage.
     *
     * @param tasks The list of tasks to be filtered by date.
     * @param storage The storage, which is not used by this command.
     * @return A string representation of the tasks scheduled for the specified date.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList tasksOnDate = tasks.getTasksOnDate(date);

        if (tasksOnDate.isEmpty()) {
            return "No tasks found for " + date.toLocalDate().toString() + ".";
        }

        StringBuilder result = new StringBuilder("Here are the tasks on " + date.toLocalDate().toString() + ":");
        for (int i = 0; i < tasksOnDate.size(); i++) {
            result.append("\n").append(i + 1).append(". ").append(tasksOnDate.get(i));
        }

        return result.toString();
    }
}
