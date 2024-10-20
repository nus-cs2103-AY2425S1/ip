package hue.command;

import hue.HueException;
import hue.ui.Ui;
import hue.storage.Storage;
import hue.task.Deadline;
import hue.task.Task;
import hue.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Creates an {@code AddDeadlineCommand} with the specified command string.
     *
     * @param fullCommand The full command string.
     * @throws HueException If the command string is invalid or incomplete.
     */
    public AddDeadlineCommand(String fullCommand) throws HueException {
        try {
            String[] parts = fullCommand.split("/by");
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new HueException("The description and date of a deadline cannot be empty.");
            }
            this.description = parts[0].substring(9).trim();
            this.by = parts[1].trim();
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new HueException("Please provide both a description and a date for the deadline");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, HueException {
        Task newTask = new Deadline(description, by);
        tasks.add(newTask);

        ui.showLine();

        storage.saveTasks(tasks);  // Save the updated task list
        return ui.showAddTask(newTask, tasks.size());

    }
}

