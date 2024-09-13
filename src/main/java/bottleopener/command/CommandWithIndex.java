package bottleopener.command;

import bottleopener.task.Tasklist;

/**
 * The {@code CommandWithIndex} class provides a base for commands that operate on a specific task
 * identified by an index.
 */
public abstract class CommandWithIndex implements Command {
    protected Tasklist tasklist;
    protected final int index;

    /**
     * Constructs a {@code CommandWithIndex} object with the given task list and task index.
     * This constructor is used by subclasses to initialize the task list and index for the command.
     *
     * @param tasklist The list of tasks on which the command operates.
     * @param index The index of the task in the task list to be affected by the command.
     */
    public CommandWithIndex(Tasklist tasklist, int index) {
        this.tasklist = tasklist;
        this.index = index;
    }
}
