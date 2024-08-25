package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.TaskList;
import calebyyy.Ui;
import calebyyy.exceptions.CalebyyyException;

/**
 * Represents a command that the user can input.
 */
public abstract class Command {
    protected Calebyyy calebyyy;
    protected TaskList taskList;
    protected Ui ui;

    /**
     * Constructor for Command.
     * 
     * @param calebyyy The main Calebyyy object.
     * @param ui The Ui object responsible for user interaction.
     * @param taskList The TaskList object responsible for storing tasks.
     */
    public Command(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        this.calebyyy = calebyyy;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Executes the command.
     * 
     * @param input The user input.
     * @throws CalebyyyException If an error occurs during execution.
     */
    public abstract void execute(String input) throws CalebyyyException;
}