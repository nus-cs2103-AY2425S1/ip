package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.exceptions.InvalidArgumentException;
import calebyyy.TaskList;
import calebyyy.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    /**
     * Constructor for MarkCommand.
     * 
     * @param calebyyy The main Calebyyy object.
     * @param ui The Ui object responsible for user interaction.
     * @param taskList The TaskList object responsible for storing tasks.
     */
    public MarkCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    /**
     * Marks a task as done.
     * 
     * @param input The user input.
     * @throws InvalidArgumentException If the user input is invalid.
     */
    @Override
    public void execute(String input) throws InvalidArgumentException{        
        String[] parts = input.split(" ");
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new InvalidArgumentException();
        }
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(' ') + 1)); 
        taskList.markTask(taskNumber);
    }
}
