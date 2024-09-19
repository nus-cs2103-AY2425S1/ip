package jeriel.command;

import jeriel.task.Deadline;
import jeriel.task.Task;
import jeriel.util.TaskList;
import jeriel.util.JerielException;
import jeriel.util.Storage;
import jeriel.util.Ui;

import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String arguments) {
        String[] parts = arguments.split(" /by ");
        this.description = parts[0].trim();
        this.by = parts.length > 1 ? parts[1].trim() : "";
    }

    /**
     * Adds a deadline to the task list and saves the task list to file.
     * @param tasks the task list to add the deadline to
     * @param ui the ui to display the result (not used in GUI mode)
     * @param storage the storage to save to
     * @return String result for the GUI
     * @throws JerielException if description or due date is empty
     * @throws IOException if an error occurs while saving
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException {
        if (description.isEmpty() || by.isEmpty()) {
            throw new JerielException("The description and due date of a deadline cannot be empty.");
        }
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", task, tasks.size());
    }
}
