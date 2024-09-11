package susan.command;

import susan.task.Task;
import susan.ui.Storage;
import susan.task.TaskList;
import susan.ui.Ui;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Represents a command to remind the user of approaching deadline and event tasks.
 */
public class RemindCommand extends Command {
    LocalDate today = LocalDate.now();

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList remindTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            if (DAYS.between(today, task.getDate()) < 3) {
                remindTasks.add(task);
            }
        }
        return ui.showReminders(remindTasks);
    }
}