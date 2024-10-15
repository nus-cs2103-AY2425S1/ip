package chatkaki.commands;

import chatkaki.Ui;
import chatkaki.DateTimeHelper;
import chatkaki.tasks.Deadline;
import chatkaki.tasks.TaskList;

import java.time.LocalDateTime;

/**
 * Represents a command to add a deadline task.
 */
public class CommandDeadline extends Command {
    private String[] inputs;

    /**
     * Constructs a CommandDeadline object with the specified inputs.
     *
     * @param inputs The inputs for the command.
     */
    public CommandDeadline(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Executes the command to add a deadline task.
     */
    @Override
    public String execute() {
        String[] parts = inputs[1].split(" /by ");
        if (parts.length != 2 || !DateTimeHelper.isValidDateFormat(parts[1])) {
            return "Invalid Deadline format";
        }
        LocalDateTime dateTime = DateTimeHelper.parseDate(parts[1]);
        return TaskList.addTask(new Deadline(false, parts[0], dateTime), false);
    }
}