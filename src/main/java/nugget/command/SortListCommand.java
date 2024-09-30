package nugget.command;

import java.util.List;
import java.util.stream.Collectors;

import nugget.Deadline;
import nugget.Event;
import nugget.Storage;
import nugget.Task;
import nugget.TaskList;
import nugget.Todo;
import nugget.Ui;
import nugget.exception.NuggetException;


/**
 * Represents a command that lists all tasks sorted by their dates.
 */
public class SortListCommand implements Command {

    /**
     * Executes the command to list tasks sorted by their dates. Todo tasks
     * are pushed to the back as they do not have a date. For deadlines and
     * events, they are sorted based on the end date and time.
     *
     * @param tasks   The list of tasks to be sorted and displayed.
     * @param ui      The user interface that displays messages to the user.
     * @param storage The storage that handles the saving of tasks.
     * @throws NuggetException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException {
        List<Task> sortedTasks = tasks.getTasks().stream()
                .sorted((task1, task2) -> {
                    if (task1 instanceof Todo) {
                        return 1; // Todo should be at the end
                    }
                    if (task2 instanceof Todo) {
                        return -1; // Todo should be at the end
                    }
                    if (task1 instanceof Deadline && task2 instanceof Deadline) {
                        return ((Deadline) task1).getDateTime().compareTo(((Deadline) task2).getDateTime());
                    }
                    if (task1 instanceof Event && task2 instanceof Event) {
                        return ((Event) task1).getEnd().compareTo(((Event) task2).getEnd());
                    }
                    if (task1 instanceof Deadline && task2 instanceof Event) {
                        return ((Deadline) task1).getDateTime().compareTo(((Event) task2).getEnd());
                    }
                    if (task1 instanceof Event && task2 instanceof Deadline) {
                        return ((Event) task1).getEnd().compareTo(((Deadline) task2).getDateTime());
                    }
                    return 0;
                })
                .collect(Collectors.toList());

        StringBuilder output = new StringBuilder();
        if (sortedTasks.isEmpty()) {
            output.append("No tasks found!\n");
        } else {
            output.append("You have the following tasks sorted by dates:\n");
            for (int i = 0; i < sortedTasks.size(); i++) {
                output.append(String.format("%d.%s\n", i + 1, sortedTasks.get(i)));
            }
        }

        ui.showMessage(output.toString());
    }
}
