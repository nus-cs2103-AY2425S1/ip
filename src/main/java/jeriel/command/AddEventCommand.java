package jeriel.command;
import jeriel.task.*;
import jeriel.util.*;
import java.io.IOException;

public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String arguments) {
        String[] parts = arguments.split(" /from | /to ");
        this.description = parts[0].trim();
        this.from = parts.length > 1 ? parts[1].trim() : "";
        this.to = parts.length > 2 ? parts[2].trim() : "";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new JerielException("The description, start time, and end time of an event cannot be empty.");
        }
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
