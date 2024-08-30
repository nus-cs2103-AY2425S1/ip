import java.io.IOException;

public class Yapper {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Yapper(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                CommandType command = Parser.parseCommand(fullCommand);

                switch (command) {
                    case LIST:
                        ui.printTasks(tasks);
                        break;
                    case MARK:
                        handleMark(fullCommand);
                        break;
                    case UNMARK:
                        handleUnmark(fullCommand);
                        break;
                    case TODO:
                        handleTodo(fullCommand);
                        break;
                    case DEADLINE:
                        handleDeadline(fullCommand);
                        break;
                    case EVENT:
                        handleEvent(fullCommand);
                        break;
                    case DELETE:
                        handleDelete(fullCommand);
                        break;
                    case BYE:
                        isExit = true;
                        ui.printGoodbye();
                        break;
                    default:
                        throw new YapperException("Unknown command");
                }

                storage.save(tasks.getTasks());
            } catch (YapperException | IOException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    private void handleMark(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new YapperException("The mark command requires a task number.");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new YapperException("Invalid task number.");
        }
        Task task = tasks.getTask(taskIndex);
        task.markAsDone();
        ui.printTaskMarked(task);
    }

    private void handleUnmark(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new YapperException("The unmark command requires a task number.");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new YapperException("Invalid task number.");
        }
        Task task = tasks.getTask(taskIndex);
        task.markAsNotDone();
        ui.printTaskUnmarked(task);
    }

    private void handleTodo(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2) {
            throw new YapperException("The todo command requires a description.");
        }
        Task task = new Todo(parts[1]);
        tasks.addTask(task);
        ui.printTaskAdded(task, tasks.getSize());
    }

    private void handleDeadline(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" /by ");
        if (parts.length < 2) {
            throw new YapperException("The deadline command requires a description and a deadline with time.");
        }
        String description = parts[0].substring(9).trim(); 
        Task task = new Deadline(description, parts[1]);  
        tasks.addTask(task);
        ui.printTaskAdded(task, tasks.getSize());
    }
    
    private void handleEvent(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" /from ");
        if (parts.length < 2) {
            throw new YapperException("The event command requires a description and a start time.");
        }
        String[] times = parts[1].split(" /to ");
        if (times.length < 2) {
            throw new YapperException("The event command requires both a start and end time.");
        }
        String description = parts[0].substring(6).trim(); 
        Task task = new Event(description, times[0], times[1]);  
        tasks.addTask(task);
        ui.printTaskAdded(task, tasks.getSize());
    }

    private void handleDelete(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new YapperException("The delete command requires a task number.");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new YapperException("Invalid task number.");
        }
        Task task = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        ui.printTaskRemoved(task, tasks.getSize());
    }

    public static void main(String[] args) {
        new Yapper("data/tasks.txt").run();
    }
}
