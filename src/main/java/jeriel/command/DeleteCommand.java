package jeriel.command;

import jeriel.task.Task;
import jeriel.util.TaskList;
import jeriel.util.JerielException;
import jeriel.util.Storage;
import jeriel.util.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(String arguments) throws JerielException {
        try {
            this.taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new JerielException("Invalid task number. Please enter a valid task number.");
        }
    }

    /**
     * Deletes a task from the task list and saves the task list to file.
     * @param tasks the task list to delete the task from
     * @param ui the ui to display the result (not used in GUI mode)
     * @param storage the storage to save to
     * @return String result for the GUI
     * @throws JerielException if task number is invalid
     * @throws IOException if an error occurs while saving
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new JerielException("Invalid task number. Please enter a valid task number.");
        }
        Task task = tasks.get(taskIndex);
        tasks.deleteTask(taskIndex);
        storage.save(tasks.getTasks());
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.", task, tasks.size());
    }
}
