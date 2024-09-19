package jeriel.command;

import jeriel.task.Task;
import jeriel.util.JerielException;
import jeriel.util.Storage;
import jeriel.util.TaskList;
import jeriel.util.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(String arguments) throws JerielException {
        try {
            this.taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new JerielException("Invalid task number. Please enter a valid task number.");
        }
    }

    /**
     * Unmarks the task and saves the task list.
     *
     * @param tasks the task list to unmark the task from
     * @param ui the ui to display the result (not used in GUI mode)
     * @param storage the storage to save the task list to
     * @return String response to show in GUI
     * @throws JerielException if the task index is invalid
     * @throws IOException if there is an error saving the task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new JerielException("Invalid task number. Please enter a valid task number.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        storage.save(tasks.getTasks());
        return String.format("OK, I've marked this task as not done yet:\n%s", task);
    }
}
