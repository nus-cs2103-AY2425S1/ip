package alice.command;

import alice.storage.TaskList;
import alice.task.InvalidTaskException;

/** Commands for all actions. */
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
        TAG,
    }

    protected final TaskList taskList;

    protected Command(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Return a Command based on the input type.
     * Each input line from the user starts with a verb,
     * which can be used to choose which type of Command to return.
     *
     * @param  line     the input line from the user
     * @param  taskList the taskList instance
     * @return          the parsed command
     */
    public static Command fromInput(String line, TaskList taskList) throws IllegalArgumentException {
        String input = line.toUpperCase();
        boolean isToDo = input.startsWith(Action.TODO.name());
        boolean isDeadline = input.startsWith(Action.DEADLINE.name());
        boolean isEvent = input.startsWith(Action.EVENT.name());
        if (isToDo || isDeadline | isEvent) {
            return new AddTask(taskList);
        }

        if (input.startsWith(Action.LIST.name())) {
            return new ListTask(taskList);
        }

        if (input.startsWith(Action.MARK.name())) {
            return new MarkTask(taskList);
        }

        if (input.startsWith(Action.UNMARK.name())) {
            return new UnmarkTask(taskList);
        }

        if (input.startsWith(Action.DELETE.name())) {
            return new DeleteTask(taskList);
        }

        if (input.startsWith(Action.FIND.name())) {
            return new FindTask(taskList);
        }

        if (input.startsWith(Action.TAG.name())) {
            return new TagTask(taskList);
        }

        throw new IllegalArgumentException("Unsupported command.");
    }

    /**
     * Runs the given command based on the input.
     * The argument parsing will be implemented by subclasses.
     *
     * @param input the input line from the user
     */
    public abstract String execute(String input) throws InvalidTaskException;
}
