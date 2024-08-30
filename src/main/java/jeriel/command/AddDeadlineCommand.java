package jeriel.command;
import jeriel.task.*;
import jeriel.util.*;

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
     * Adds a deadline to the task list, and saves the task list to file.
     *
     * @param tasks the task list to add the deadline to
     * @param ui the ui to display the result
     * @param storage the storage to save to
     * @throws JerielException if the description or due date are empty
     * @throws IOException if there is an error saving the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException {
        if (description.isEmpty() || by.isEmpty()) {
            throw new JerielException("The description and due date of a deadline cannot be empty.");
        }
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
