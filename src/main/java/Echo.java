import echo.*;

import java.io.FileNotFoundException;

import java.io.IOException;

/**
 * The Echo class is the main entry point for the Echo application.
 * It handles the loading, saving, and processing of user commands related to task management.
 */
public class Echo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static final String DOCS_TASKS_TXT = "docs/tasks.txt";

    /**
     * Constructs an Echo object and initializes the necessary components.
     *
     * @param filePath The file path where tasks are stored and loaded.
     */
    public Echo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (EchoException e) { //empty file or file does not exist

            ui.showLoadingError();
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Runs the main loop of the Echo application, processing user commands and interacting with the user interface.
     */
    public void run() {
        ui.showWelcomeMessage();

        while (true) {
            String input = ui.readCommand();
            String[] parts = Parser.parseCommand(input);
            String command = parts[0];



            try {
                switch (command) {
                case "bye":
                    storage.save(tasks.getTasks());
                    ui.showGoodbyeMessage();
                    return;
                case "list":
                    ui.showTaskList(tasks.getTasks());
                    break;
                case "mark":
                    tasks.markTask(Integer.parseInt(parts[1]));
                    ui.showMarkedTask(tasks.getTask(Integer.parseInt(parts[1])));
                    break;
                case "unmark":
                    tasks.unmarkTask(Integer.parseInt(parts[1]));
                    ui.showUnmarkedTask(tasks.getTask(Integer.parseInt(parts[1])));
                    break;
                case "todo":
                    Task todo = tasks.addTodo(parts[1]);
                    ui.showTaskAdded(todo, tasks.getTasks().size());
                    break;
                case "deadline":
                    Deadline deadline = tasks.addDeadline(parts[1]);
                    ui.showTaskAdded(deadline, tasks.getTasks().size());
                    break;
                case "event":
                    Events event = tasks.addEvent(parts[1]);
                    ui.showTaskAdded(event, tasks.getTasks().size());
                    break;
                case "delete":
                    Task removedTask = tasks.deleteTask(Integer.parseInt(parts[1]));
                    ui.showTaskRemoved(removedTask, tasks.getTasks().size());
                    break;
                case "find":
                    String toFind = parts[1];
                    tasks.find(toFind);
                    break;
                default:
                    ui.showErrorMessage("I'm sorry, but I don't know what that means :-(");
                }
            } catch (EchoException | IOException e) {
                ui.showErrorMessage(e.getMessage());
            }

        }
    }

    /**
     * The main method that creates an instance of Echo and starts the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Echo echo = new Echo(DOCS_TASKS_TXT);
        echo.run();
    }


}
