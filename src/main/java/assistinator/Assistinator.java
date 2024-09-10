package assistinator;

/**
 * Main application
 */
public class Assistinator {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initialising an Assistinator class
     * @param filePath path to storage file
     */
    public Assistinator(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (AssitinatorExceptions e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the application
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.parseCommand(fullCommand);
                String response = executeCommand(command, fullCommand);
                assert response != null : "Response should not be null";
                ui.showResponse(response);
                if (command == Command.BYE) {
                    isRunning = false;
                }
            } catch (AssitinatorExceptions e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            String response = executeCommand(command, input);
            assert response != null : "Response should not be null";
            return response;
        } catch (AssitinatorExceptions e) {
            return e.getMessage();
        }
    }

    private String executeCommand(Command command, String fullCommand) throws AssitinatorExceptions {
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
    public static void main(String[] args) {
        new Assistinator("./data/assistinator.txt").run();
    }
}
