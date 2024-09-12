package jeff.command;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;

/**
 * Represents a command that lists all tasks currently stored in the task list.
 * This command retrieves each task and displays it to the user, providing a complete view of all tasks.
 */
public class ListCommand extends Command {
    private StringBuilder sb;
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        sb = new StringBuilder();
        ui.showMessage("Here are your current tasks: ");
        String taskListString = IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.getTask(i))
                .collect(Collectors.joining("\n"));

        ui.showMessage(taskListString);
    }
}
