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
