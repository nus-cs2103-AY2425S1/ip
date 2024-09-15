package wolfie.command;

import java.io.IOException;
import java.util.stream.Collectors;

import wolfie.exception.WolfieException;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Excutes the list command to list all tasks in the task list.
     * @param tasks   The list of tasks
     * @param ui      The user interface
     * @param storage The storage object
     * @return A message with all the tasks in the task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException {
        String taskListString = tasks.getTasks().stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
        if (taskListString.isEmpty()) {
            return "The task list is empty!\n"
                    + "Looks like you're free for now! :)";
        }
        return ui.showTaskList(taskListString);
    }
}
