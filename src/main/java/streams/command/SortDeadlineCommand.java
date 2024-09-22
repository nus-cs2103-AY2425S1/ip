package streams.command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import streams.task.DeadlineTask;
import streams.task.Task;
import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

/**
 * Represents a command to sort and display deadline tasks.
 */
public class SortDeadlineCommand extends Command {

    /**
     * Executes the sort deadline command, sorting and displaying deadline tasks.
     *
     * @param tasks The task list to sort and display from.
     * @param ui The user interface to show the sorted tasks.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> deadlineTasks = tasks.getTasks().stream()
                .filter(task -> task instanceof DeadlineTask)
                .sorted(Comparator.comparing(task -> ((DeadlineTask) task).getBy()))
                .collect(Collectors.toCollection(ArrayList::new));

        if (deadlineTasks.isEmpty()) {
            ui.showMessage("No deadline tasks to sort");
        } else {
            ui.showMessage("Deadline tasks sorted by due date:");
            for (int i = 0; i < deadlineTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + deadlineTasks.get(i));
            }
        }
    }
}
