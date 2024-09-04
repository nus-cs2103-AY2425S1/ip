package arsenbot.command;

import arsenbot.storage.Storage;
import arsenbot.task.*;
import arsenbot.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    private final String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TaskManagerException {
        Task task;
        if (input.startsWith("todo")) {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new TaskManagerException("Error: The description of a 'todo' cannot be empty.");
            }
            task = new Todo(description);
        } else if (input.startsWith("deadline")) {
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new TaskManagerException("Error: The deadline command requires both a task description and a '/by' date.");
            }
            task = new Deadline(parts[0].trim(), parts[1].trim());
        } else {
            String[] parts = input.substring(6).split(" /from ");
            if (parts.length < 2 || parts[0].trim().isEmpty() || !parts[1].contains(" /to ")) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            String description = parts[0].trim();
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts[1].isEmpty()) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            task = new Event(description, timeParts[0].trim(), timeParts[1].trim());
        }
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
