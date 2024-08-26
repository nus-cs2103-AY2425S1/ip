public abstract class Command {
    private enum Action {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    protected final Ui ui;
    protected final TaskList taskList;

    protected Command(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

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

        throw new IllegalArgumentException("Unsupported command.");
    }

    public abstract void execute(String line);
}