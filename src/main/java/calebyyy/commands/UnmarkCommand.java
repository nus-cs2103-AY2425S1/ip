package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.exceptions.InvalidArgumentException;
import calebyyy.TaskList;
import calebyyy.Ui;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructor for UnmarkCommand.
     * 
     * @param calebyyy The main Calebyyy object.
     * @param ui The Ui object responsible for user interaction.
     * @param taskList The TaskList object responsible for storing tasks.
     */
    public UnmarkCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    /**
     * Unmarks a task as done.
     * 
     * @param input The user input.
     * @throws InvalidArgumentException If the user input is invalid.
     */
    @Override
    public void execute(String input) throws InvalidArgumentException {
        String[] parts = input.split(" ");
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new InvalidArgumentException();
        }

        int taskNumber = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
        taskList.unmarkTask(taskNumber);
    }
}