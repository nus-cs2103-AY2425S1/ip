package jeriel.command;

import jeriel.task.Event;
import jeriel.task.Task;
import jeriel.util.TaskList;
import jeriel.util.JerielException;
import jeriel.util.Storage;
import jeriel.util.Ui;

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

    /**
     * Adds an event to the task list and saves the task list to file.
     * @param tasks the task list to add the event to
     * @param ui the ui to display the result (not used in GUI mode)
     * @param storage the storage to save to
     * @return String result for the GUI
     * @throws JerielException if description, start time, or end time are empty
     * @throws IOException if an error occurs while saving
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new JerielException("The description, start time, and end time of an event cannot be empty.");
        }
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", task, tasks.size());
    }
}
