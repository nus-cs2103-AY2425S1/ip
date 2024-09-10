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
     * @param ui      the user interface
     * @param tasks   the task list
     * @param storage the storage for tasks
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
     * @param fullCommand the full user input
     * @return the parsed command
     * @throws AssitinatorExceptions if the command cannot be parsed
     */
    public Command parseCommand(String fullCommand) throws AssitinatorExceptions {
        return parser.parseCommand(fullCommand);
    }

    /**
     * Executes the given command with the provided input.
     *
     * @param command     the command to execute
     * @param fullCommand the full user input
     * @return the response to the executed command
     * @throws AssitinatorExceptions if the command cannot be executed
     */
    public String executeCommand(Command command, String fullCommand) throws AssitinatorExceptions {
        switch (command) {
        case BYE:
            return "Bye. Hope to see you again soon!";
        case LIST:
            return tasks.listTasks();
        case MARK:
        case UNMARK:
            int index = parser.parseIndex(fullCommand);
            tasks.markTask(index, command == Command.MARK);
            storage.saveTasks(tasks.getTasks());
            return tasks.listTasks();
        case TODO:
        case DEADLINE:
        case EVENT:
            Task newTask = parser.parseTask(command, fullCommand);
            tasks.addTask(newTask);
            storage.saveTasks(tasks.getTasks());
            return "Task added successfully\nNumber of Tasks: " + tasks.size();
        case DELETE:
            int deleteIndex = parser.parseIndex(fullCommand);
            tasks.deleteTask(deleteIndex);
            storage.saveTasks(tasks.getTasks());
            return "Task " + (deleteIndex + 1) + " deleted successfully. Number of Tasks: " + tasks.size();
        case FIND:
            return tasks.filterTasks(fullCommand.substring(fullCommand.indexOf(' ') + 1));
        default:
            throw new AssitinatorExceptions("Command does not exist");
        }
    }
}
