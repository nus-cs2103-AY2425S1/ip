package LunaBot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import LunaBot.exception.LunaBotException;
import LunaBot.storage.Storage;
import LunaBot.task.Event;
import LunaBot.task.TaskList;
import LunaBot.ui.Ui;


/**
 * Command to add Event task to the taskList.
 */
public class AddEventCommand extends Command {
    private String[] parts;
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an AddEventCommand from user input.
     *
     * @param input Full user input is taken in for the command.
     * @throws LunaBotException Handles wrong input format, empty description,
     *                          or wrong date/time format.
     */
    public AddEventCommand(String input) throws LunaBotException {
        this.parts = input.substring(6).split(" /from | /to ");
        if (parts.length < 3) {
            throw new LunaBotException("Invalid event format");
        }
        if (parts[0].isEmpty()) {
            throw new LunaBotException("Description of task cannot be empty");
        }
        if (parts[1].isEmpty()) {
            throw new LunaBotException("Start time of task cannot be empty");
        }
        if (parts[2].isEmpty()) {
            throw new LunaBotException("End time of task cannot be empty");
        }
        this.description = parts[0];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.from = LocalDateTime.parse(parts[1], formatter);
            this.to = LocalDateTime.parse(parts[2], formatter);
        } catch (DateTimeParseException e) {
            throw new LunaBotException("Invalid event date/time format. Use yyyy-MM-dd HH:mm");
        }
    }

    /**
     * @param taskList Current list of tasks for the new Event task to be added to.
     * @param ui User interface that handles user input interactions and display messages.
     * @param storage Storage system to save or load tasks to/from a file.
     * @throws LunaBotException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LunaBotException {
        Event event = new Event(description, from, to);
        taskList.addTask(event);
        storage.save(taskList.getTasks());
        ui.printTaskAdded(event, taskList.size());
    }
}
