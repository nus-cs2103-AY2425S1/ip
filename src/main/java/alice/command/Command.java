package alice.command;

import alice.storage.TaskList;
import alice.ui.Ui;

public abstract class Command {
    /** Actions which the user can take. */
    private enum Action {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
    }

    protected final Ui ui;
    protected final TaskList taskList;

    protected Command(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Return a Command based on the input type.
     * Each input line from the user starts with a verb,
     * which can be used to choose which type of Command to return.
     *
     * @param  line     the input line from the user
     * @param  ui       the ui instance
     * @param  taskList the taskList instance
     * @return          the parsed command
     */
    public static Command fromInput(String line, Ui ui, TaskList taskList) throws IllegalArgumentException {
        String input = line.toUpperCase();
        if (input.startsWith(Action.TODO.name()) ||
            input.startsWith(Action.DEADLINE.name()) ||
            input.startsWith(Action.EVENT.name())) {
            return new AddTask(ui, taskList);
        }

        if (input.startsWith(Action.LIST.name())) {
            return new ListTask(ui, taskList);
        }

        if (input.startsWith(Action.MARK.name())) {
            return new MarkTask(ui, taskList);
        }

        if (input.startsWith(Action.UNMARK.name())) {
            return new UnmarkTask(ui, taskList);
        }

        if (input.startsWith(Action.DELETE.name())) {
            return new DeleteTask(ui, taskList);
        }

        if (input.startsWith(Action.FIND.name())) {
            return new FindTask(ui, taskList);
        }

        throw new IllegalArgumentException("Unsupported command.");
    }

    /**
     * Runs the given command based on the input.
     * The argument parsing will be implemented by subclasses.
     *
     * @param line the input line from the user
     */
    public abstract void execute(String line);
}