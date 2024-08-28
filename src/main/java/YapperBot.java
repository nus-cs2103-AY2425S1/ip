public class YapperBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private static final String FILE_PATH = "./data/savedFile.txt";

    public YapperBot() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (YapperBotException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printStartMessage();

        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.readCommand(); // scanner.nextLine()
            Commands userCommand = parser.parseCommand(userInput); // return command variable
            isRunning = executeCommand(userCommand, userInput);
        }
    }

    // takes in both command and original input cuz parser need to parse original input for each command case
    private boolean executeCommand(Commands command, String userInput) {
        switch (command) {
        case BYE:
            storage.saveTasks(tasks.getTasks());
            ui.printEndMessage();
            return false;
        case LIST:
            ui.showTaskList(tasks);
            break;
        case MARK:
            parser.markCommand(tasks, userInput, storage);
            break;
        case UNMARK:
            parser.unmarkCommand(tasks, userInput, storage);
            break;
        case TODO:
            parser.todoCommand(tasks, userInput, storage);
            break;
        case DEADLINE:
            parser.deadlineCommand(tasks, userInput, storage);
            break;
        case EVENT:
            parser.eventCommand(tasks, userInput, storage);
            break;
        case DELETE:
            parser.deleteCommand(tasks, userInput, storage);
            break;
        default:
            ui.printInvalidCommandMessage();
        }
        return true;
    }

    public static void main(String[] args) {
        new YapperBot().run();
    }
}
