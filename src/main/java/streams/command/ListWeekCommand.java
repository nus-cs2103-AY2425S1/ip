package streams.command;

import java.time.LocalDate;
import java.util.ArrayList;

import streams.task.DeadlineTask;
import streams.task.EventTask;
import streams.task.Task;
import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

/**
 * Represents a command to list tasks for the upcoming week.
 */
public class ListWeekCommand extends Command {

    /**
     * Executes the list week command, showing tasks for the next 7 days.
     *
     * @param tasks The task list to search in.
     * @param ui The user interface to display the results.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekLater = today.plusDays(7);

        ArrayList<Task> tasksThisWeek = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task instanceof DeadlineTask) {
                LocalDate deadlineDate = ((DeadlineTask) task).getBy().toLocalDate();
                if (!deadlineDate.isBefore(today) && !deadlineDate.isAfter(oneWeekLater)) {
                    tasksThisWeek.add(task);
                }
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                LocalDate eventStartDate = eventTask.getFrom().toLocalDate();
                LocalDate eventEndDate = eventTask.getTo().toLocalDate();
                if (!(eventEndDate.isBefore(today) || eventStartDate.isAfter(oneWeekLater))) {
                    tasksThisWeek.add(task);
                }
            }
        }

        if (tasksThisWeek.isEmpty()) {
            ui.showMessage("no tasks in the upcoming week");
        } else {
            ui.showMessage("tasks in the upcoming week:");
            for (int i = 0; i < tasksThisWeek.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasksThisWeek.get(i));
            }
        }
    }
}
