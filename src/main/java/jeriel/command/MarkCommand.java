package jeriel.command;

import jeriel.task.Task;
import jeriel.util.JerielException;
import jeriel.util.Storage;
import jeriel.util.TaskList;
import jeriel.util.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(String arguments) throws JerielException {
        try {
            this.taskIndex = Integer.parseInt(arguments.trim()) - 1; // Ensure argument is properly trimmed
        } catch (NumberFormatException e) {
            throw new JerielException("Invalid task number. Please enter a valid task number.");
        }
    }

    /**
     * Marks a task as done and saves the task list to file.
     * 
     * @param tasks   the task list to mark the task in
     * @param ui      the ui to display the result (not used in GUI mode)
     * @param storage the storage to save the updated task list
     * @return String response to show in GUI
     * @throws JerielException if the task number is invalid
     * @throws IOException     if there is an error saving the task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new JerielException("Invalid task number. Please enter a valid task number.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        storage.save(tasks.getTasks());
        return String.format("Nice! I've marked this task as done:\n%s", task);
    }
}
