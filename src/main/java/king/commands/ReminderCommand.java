package king.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.task.Deadline;
import king.task.Event;
import king.task.Task;
import king.ui.Ui;

/**
 * Represents a command to find tasks that are due within a specified number of days.
 */
public class ReminderCommand extends Command {
    private final int daysFromNow;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param daysFromNow The keyword to search for in task descriptions.
     */
    public ReminderCommand(String daysFromNow) {
        this.daysFromNow = Integer.parseInt(daysFromNow);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thresholdDate = now.plusDays(this.daysFromNow);

        ArrayList<Task> tasksDueSoon = tasks.getTaskList().stream()
                .filter(task -> !task.getStatus()).filter(task -> task instanceof Deadline || task instanceof Event)
                .filter(task -> task.getDueDateTime().isAfter(now) && task.getDueDateTime().isBefore(thresholdDate))
                .sorted(Comparator.comparing(Task::getDueDateTime))
                .collect(Collectors.toCollection(ArrayList::new));

        if (tasksDueSoon.isEmpty()) {
            return ui.showNoTasksDue(daysFromNow);
        } else {
            return ui.showTasksDueSoon(tasksDueSoon, daysFromNow);
        }
    }

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return {@code false} as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

