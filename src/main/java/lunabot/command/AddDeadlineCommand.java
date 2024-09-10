package lunabot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lunabot.exception.LunaBotException;
import lunabot.storage.Storage;
import lunabot.task.Deadline;
import lunabot.task.TaskList;
import lunabot.ui.Ui;

/**
 * Command to add Deadline task to the taskList.
 */
public class AddDeadlineCommand extends Command {
    private String[] parts;
    private String description;
    private LocalDateTime by;

    /**
     * Constructs an AddDeadlineCommand from user input.
     *
     * @param input Full user input is taken in for the command.
     * @throws LunaBotException Handles wrong input format, empty description
     *                          or wrong date/time format.
     */
    public AddDeadlineCommand(String input) throws LunaBotException {
        this.parts = input.substring(9).split(" /by ");
        if (parts.length < 2) {
            throw new LunaBotException("Invalid deadline format");
        }
        if (parts[0].isEmpty()) {
            throw new LunaBotException("Description of task cannot be empty");
        }
        if (parts[1].isEmpty()) {
            throw new LunaBotException("LunaBot.task.Deadline of task cannot be empty");
        }
        this.description = parts[0];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.by = LocalDateTime.parse(parts[1], formatter);
        } catch (DateTimeParseException e) {
            throw new LunaBotException("Invalid deadline date/time format. Use yyyy-MM-dd HH:mm");
        }
    }

    /**
     * @param taskList Current list of tasks for the new Deadline task to be added to.
     * @param ui User interface that handles interactions and display messages.
     * @param storage Storage system to save or load tasks to/from a file.
     * @throws LunaBotException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LunaBotException {
        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        storage.save(taskList.getTasks());
        ui.printTaskAdded(deadline, taskList.size());
    }
}
