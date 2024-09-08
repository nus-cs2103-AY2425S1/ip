package qwerty.command;

import java.util.HashMap;

import qwerty.QwertyException;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.ui.Ui;

/**
 * This class encapsulates a command executable by the chatbot.
 */
public abstract class Command {
    /** The arguments for each parameter of the command */
    private HashMap<String, String> args;

    public Command(HashMap<String, String> args) {
        this.args = args;
    }

    /**
     * Returns the map containing the arguments given to this command.
     *
     * @return Map of parameter-argument pairs.
     */
    public HashMap<String, String> getArgs() {
        return this.args;
    }

    /**
     * Returns true if this command is an exit command. This means that it will
     * cause the chatbot to halt. Returns false otherwise.
     *
     * @return True if this command is an exit command, otherwise false
     */
    public abstract boolean isExitCommand();

    /**
     * Executes the given command using the given TaskList, Ui and Storage components.
     *
     * @param tasks The TaskList component handling storage of Task objects.
     * @param ui The Ui component handling user interaction.
     * @param storage The Storage component handling read/write to hard disk.
     * @throws QwertyException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws QwertyException;
}
