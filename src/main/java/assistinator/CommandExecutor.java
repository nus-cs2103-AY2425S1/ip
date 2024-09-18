package assistinator;

/**
 * Responsible for executing commands in the Assistinator application.
 */
public class CommandExecutor {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    /**
     * Initializes the CommandExecutor with the necessary dependencies.
     *
     * @param ui The user interface.
     * @param tasks The task list.
     * @param storage The storage for tasks.
     */
    public CommandExecutor(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
        this.parser = new Parser();
    }

    /**
     * Parses the user command from the given input.
     *
     * @param fullCommand The full user input.
     * @return The parsed command.
     * @throws AssitinatorException If the command cannot be parsed.
     */
    public Command parseCommand(String fullCommand) throws AssitinatorException {
        return parser.parseCommand(fullCommand);
    }

    /**
     * Executes the given command with the provided input.
     *
     * @param command The command to execute.
     * @param fullCommand The full user input.
     * @return The response to the executed command.
     * @throws AssitinatorException If the command cannot be executed.
     */
    public String executeCommand(Command command, String fullCommand) throws AssitinatorException {
        switch (command) {
        case BYE:
            storage.saveTasks(tasks.getTasks());
            return "Bye. Hope to see you again soon!";
        case LIST:
            return tasks.listTasks();
        case MARK:
        case UNMARK:
            int index = parser.parseIndex(fullCommand);
            tasks.markTask(index, command == Command.MARK);
            return tasks.listTasks();
        case TODO:
        case DEADLINE:
        case EVENT:
            Task newTask = parser.parseTask(command, fullCommand);
            Task clashingTask = tasks.hasTimeClash(newTask);
            if (clashingTask != null) {
                return String.format("Warning: This event clashes with %s.", clashingTask.getDescription());
            }
            tasks.addTask(newTask);
            return "Task added successfully\nYour evil agenda contains " + tasks.size() + " tasks";
        case DELETE:
            int deleteIndex = parser.parseIndex(fullCommand);
            tasks.deleteTask(deleteIndex);
            return "Task " + (deleteIndex + 1) + " deleted successfully\n" +
                    "Your evil agenda contains " + tasks.size() + " tasks";
        case FIND:
            return tasks.filterTasks(fullCommand.substring(fullCommand.indexOf(' ') + 1));
        default:
            throw new AssitinatorException("This command does not exist doctor");
        }
    }
}
