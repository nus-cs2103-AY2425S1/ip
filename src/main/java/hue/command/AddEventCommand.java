package hue.command;

import hue.HueException;
import hue.ui.ui;
import hue.storage.Storage;
import hue.task.Event;
import hue.task.Task;
import hue.task.TaskList;

import java.io.IOException;
/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Creates an {@code AddEventCommand} with the specified command string.
     *
     * @param fullCommand The full command string.
     * @throws HueException If the command string is invalid or incomplete.
     */
    public AddEventCommand(String fullCommand) throws HueException {
        try {
            String[] parts = fullCommand.split("/from|/to");
            if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new HueException("The description, start time and end time cannot be empty.");
            }
            this.description = parts[0].substring(6).trim();
            this.from = parts[1].trim();
            this.to = parts[2].trim();
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new HueException("Pleae provide a description, start time and end time for the event.");
        }
    }

    @Override
    public String execute(TaskList tasks, ui ui, Storage storage) throws IOException {
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);

        ui.showLine();

        storage.saveTasks(tasks);
        return ui.showAddTask(newTask, tasks.size());
    }


}
