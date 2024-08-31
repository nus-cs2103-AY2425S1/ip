package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.TaskList;
import calebyyy.Ui;
import calebyyy.exceptions.InvalidArgumentException;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor for DeleteCommand.
     *
     * @param calebyyy The main Calebyyy object.
     * @param ui The Ui object responsible for user interaction.
     * @param taskList The TaskList object responsible for storing tasks.
     */
    public DeleteCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    /**
     * Deletes a task.
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
        taskList.deleteTask(taskNumber);
    }
}
