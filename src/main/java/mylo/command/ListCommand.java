package mylo.command;

import mylo.task.TaskList;
import mylo.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a command to list tasks from the task list.
 * <p></p>
 * <p>The {@code ListCommand} can display all tasks in the task list, or if a specific
 * {@code LocalDateTime} is provided, it will list tasks scheduled or ongoing on that date.</p>
 * <p></p>
 * <p>If no date is provided, all tasks in the list will be displayed.</p>
 *
 * @author cweijin
 */
public class ListCommand extends Command{
    private LocalDateTime dateTime;

    /**
     * Constructs a {@code ListCommand} to list tasks scheduled on the given date.
     * <p></p>
     * <p>If a {@code LocalDateTime} is provided, the command will filter and display
     * only the tasks scheduled or ongoing on that date.</p>
     *
     * @param dateTime The specific date and time to filter tasks by.
     */
    public ListCommand(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Constructs a {@code ListCommand} to list all tasks.
     * <p></p>
     * <p>Since no date is specified, the command will list all tasks in the task list.</p>
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the list command by displaying tasks.
     * <p></p>
     * <p>If a {@code dateTime} is specified, it will display tasks scheduled on that date.
     * Otherwise, it will display all tasks in the task list.</p>
     *
     * @param list The task list whose tasks will be displayed.
     * @param ui   The user interface that shows the tasks.
     */
    @Override
    public void execute(TaskList list, Ui ui) {
        String message = list.toString();

        if (dateTime != null) {
            message = list.tasksOnDate(dateTime).toString();
        }

        ui.show(message);
    }
}
