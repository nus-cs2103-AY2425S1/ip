package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

import java.util.Map;

/**
 * Abstract base class that all commands must inherit.
 * All commands must additionally define a public and static <code>COMMAND</code> string field that
 * represents the string that the command corresponds to.
 * Classes that inherit this class and is in this package is collected by the parser to be used.
 */
public abstract class Command {
    protected final Map<String, String> arguments;

    public Command(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns true if the program should exit after executing this command.
     *
     * @return true if the program should exit, false otherwise
     */
    public abstract boolean isExit();

    /**
     * Executes this command.
     *
     * @param tasks the task list on which this command will operate
     * @param ui the Ui instance on which this command will operate
     * @param storage the Storage instance on which this command will operate
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Command)) {
            return false;
        }

        Command command = (Command) object;

        try {
            this.getClass().cast(command);
        } catch (ClassCastException e) {
            return false;
        }

        return this.arguments.equals(command.arguments);
    }
}
