package chatkaki.commands;

import chatkaki.tasks.TaskList;
import chatkaki.tasks.Event;
import chatkaki.Ui;
import chatkaki.DateTimeHelper;

import java.time.LocalDateTime;

/**
 * Represents a command to add an event task.
 */
public class CommandEvent extends Command {
    private String[] inputs;


    /**
     * Constructs a CommandEvent object with the specified inputs.
     *
     * @param inputs The inputs for the command.
     */
    public CommandEvent(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Executes the command to add an event task.
     */
    @Override
    public void execute() {
        String[] parts = inputs[1].split(" /from | /to ");
        if (parts.length != 3 ||
                !DateTimeHelper.isValidDateFormat(parts[1]) || !DateTimeHelper.isValidDateFormat(parts[2])) {
            Ui.printMessage("Invalid Event format, it should contain /from and /to with valid dates.");
            return;
        }
        LocalDateTime start = DateTimeHelper.parseDate(parts[1]);
        LocalDateTime end = DateTimeHelper.parseDate(parts[2]);
        TaskList.addTask(new Event(false, parts[0], start, end), false);
    }
}
