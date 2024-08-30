package jeriel.command;
import jeriel.task.*;
import jeriel.util.*;

import java.io.IOException;
public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(String arguments) throws JerielException {
        try {
            this.taskIndex = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new JerielException("Invalid task number. Please enter a valid task number.");
        }
    }

    /**
     * Unmarks the task with the given index as not done.
     *
     * @param tasks the task list to unmark the task from
     * @param ui the ui to display the result
     * @param storage the storage to save to
     * @throws JerielException if the task index is invalid
     * @throws IOException if there is an error saving the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new JerielException("Invalid task number. Please enter a valid task number.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
