package crack;

import java.io.IOException;

/**
 * The Crack class serves as the main entry point for the application.
 * It initializes the user interface (Ui), task storage (Storage),
 * and task management (TaskList). It also contains the main loop that
 * processes user commands.
 */
public class Crack {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Crack instance, initializing the Ui, Storage,
     * and TaskList. Attempts to load tasks from the file specified
     * by the filePath. If loading fails, an empty task list is initialized.
     *
     * @param filePath the path to the file where tasks are stored.
     */
    public Crack(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks(ui));
        } catch (IOException e) {
            ui.showError("Unable to load tasks from file.");
            tasks = new TaskList(); // Initialize with an empty task list if load fails
        }
    }

    /**
     * Starts the main loop of the application. It repeatedly reads user input,
     * parses commands, and performs actions based on the input. The loop continues
     * until the user enters the "bye" command, at which point the application
     * exits.
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();
            String command = Parser.parseCommand(input); // Get the command part of the input

            switch (command) {
            case "bye":
                ui.showGoodbye();
                ui.close();
                return;
            case "list":
                if (tasks.isEmpty()) {
                    ui.showMessage("Your task list is empty.");
                } else {
                    ui.showMessage("Here are the tasks in your list:\n" + tasks.listTasks());
                }
                break;
            case "mark":
                try {
                    int index = Parser.parseTaskNumber(input);
                    tasks.getTask(index).markAsDone();
                    ui.showMessage("Nice! I've marked this task as done:\n   " + tasks.getTask(index));
                    storage.saveTasks(tasks.getTasks(), ui);
                } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                    ui.showError(e.getMessage());
                }
                break;
            case "unmark":
                try {
                    int index = Parser.parseTaskNumber(input);
                    tasks.getTask(index).unmark();
                    ui.showMessage("OK, I've marked this task as not done yet:\n   " + tasks.getTask(index));
                    storage.saveTasks(tasks.getTasks(), ui);
                } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                    ui.showError(e.getMessage());
                }
                break;
            case "todo":
                try {
                    String description = Parser.parseTodoDescription(input);
                    Todo newTodo = new Todo(description);
                    tasks.addTask(newTodo);
                    ui.showTaskAdded(newTodo, tasks.getSize());
                    storage.saveTasks(tasks.getTasks(), ui);
                } catch (IllegalArgumentException e) {
                    ui.showError(e.getMessage());
                }
                break;
            case "deadline":
                try {
                    String[] deadlineDetails = Parser.parseDeadline(input);
                    Deadline newDeadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                    tasks.addTask(newDeadline);
                    ui.showTaskAdded(newDeadline, tasks.getSize());
                    storage.saveTasks(tasks.getTasks(), ui);
                } catch (IllegalArgumentException e) {
                    ui.showError(e.getMessage());
                }
                break;
            case "event":
                try {
                    String[] eventDetails = Parser.parseEvent(input);
                    Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                    tasks.addTask(newEvent);
                    ui.showTaskAdded(newEvent, tasks.getSize());
                    storage.saveTasks(tasks.getTasks(), ui);
                } catch (IllegalArgumentException e) {
                    ui.showError(e.getMessage());
                }
                break;
            case "find":
                try {
                    String keyword = input.substring(5).trim(); // Extract the keyword
                    if (keyword.isEmpty()) {
                        throw new IllegalArgumentException("Keyword cannot be empty.");
                    }
                    ui.showMatchingTasks(tasks.findTasks(keyword));
                } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
                    ui.showError(e.getMessage());
                }
                break;

            case "delete":
                try {
                    int index = Parser.parseTaskNumber(input);
                    Task removedTask = tasks.removeTask(index);
                    ui.showMessage("Noted. I've removed this task:\n   " + removedTask);
                    storage.saveTasks(tasks.getTasks(), ui);
                } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                    ui.showError(e.getMessage());
                }
                break;
            default:
                ui.showError("Invalid Command.");
                break;
            }
        }
    }

    /**
     * The main method that initializes the Crack application and starts its
     * execution.
     *
     * @param args command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Crack("./data/crack.txt").run(); // Passes file path to the constructor
    }
}
