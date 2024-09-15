package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.TaskList;
import calebyyy.Ui;
import calebyyy.exceptions.CalebyyyException;
import calebyyy.exceptions.InvalidArgumentException;
import calebyyy.exceptions.InvalidTaskNumberException;

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
     * Checks if the task number is valid.
     *
     * @param taskNumber The task number.
     * @return True if the task number is valid, false otherwise.
     */
    private boolean isValidTaskNumber(int taskNumber) {
        return taskNumber > 0 && taskNumber <= taskList.getTaskCount();
    }

    /**
     * Deletes a task.
     *
     * @param input The user input.
     * @throws CalebyyyException If the user input is invalid.
     */
    @Override
    public void execute(String input) throws CalebyyyException {
        String[] parts = input.split(" ");
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new InvalidArgumentException();
        }
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
        if (!isValidTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException();
        }
        taskList.deleteTask(taskNumber);
    }
}
