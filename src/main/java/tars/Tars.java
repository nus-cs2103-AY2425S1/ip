package tars;

import java.util.List;

/**
 * The main class for the TARS application, which manages tasks and interacts with the user.
 *
 * <p>The Tars class initializes the user interface, loads tasks from storage, and contains
 * the main application loop that processes user commands. It supports various commands for
 * task management, including adding, marking, unmarking, listing, removing, and finding tasks.
 */
public class Tars {
    private final TaskList tasks;
    private final UI ui;

    /**
     * Constructs a Tars object, initializing the user interface and loading tasks from storage.
     *
     * <p>The constructor attempts to load tasks from the specified file. If an error occurs
     * during loading, an empty task list is created, and an error message is displayed.
     */
    public Tars() {
        ui = new UI();
        Storage storage = new Storage("./data/tars.txt");
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.loadTasks(), storage);
        } catch (TarsException e) {
            ui.printLoadingError();
            loadedTasks = new TaskList(storage);
        }
        tasks = loadedTasks;
    }

    /**
     * Starts the main loop of the Tars application, which listens for and processes user commands.
     */
    public void run() {
        ui.printWelcome();
        boolean isRunning = true;
        while (isRunning) {
            String input = ui.readInput();
            String[] parsedInput = Parser.parse(input);
            String command = parsedInput[0];
            String arguments = parsedInput[1];

            try {
                switch (command) {
                case "bye":
                    ui.printGoodbye();
                    isRunning = false;
                    break;
                case "list":
                    ui.printResponse(tasks.listTasks());
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(arguments.trim()) - 1;
                    Task markedTask = tasks.markTaskDone(markIndex);
                    ui.taskMarkedResponse(markedTask);
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(arguments.trim()) - 1;
                    Task unmarkedTask = tasks.markTaskUndone(unmarkIndex);
                    ui.taskUnmarkedResponse(unmarkedTask);
                    break;
                case "todo":
                    Task todo = new Todo(arguments, false);
                    tasks.addTask(todo);
                    ui.taskAddedResponse(todo, tasks.getSize());
                    break;
                case "deadline":
                    String[] deadlineParts = arguments.split(" /by ", 2);
                    Task deadline = new Deadline(deadlineParts[0], false, deadlineParts[1]);
                    tasks.addTask(deadline);
                    ui.taskAddedResponse(deadline, tasks.getSize());
                    break;
                case "event":
                    String[] eventParts = arguments.split(" /from | /to ", 3);
                    Task event = new Event(eventParts[0], false, eventParts[1], eventParts[2]);
                    tasks.addTask(event);
                    ui.taskAddedResponse(event, tasks.getSize());
                    break;
                case "remove":
                    int removeIndex = Integer.parseInt(arguments.trim()) - 1;
                    tasks.removeTask(removeIndex);
                    ui.printResponse("Noted. I've removed this task.");
                    break;
                case "find":
                    List<Task> foundTasks = tasks.findTasks(arguments.trim());
                    ui.printFoundTasks(foundTasks);
                    break;
                default:
                    ui.printResponse("I'm sorry, I can't quite help you with that.");
                }
            } catch (TarsException e) {
                ui.printResponse(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Tars().run();
    }
}
