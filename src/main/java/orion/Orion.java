package orion;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import orion.commands.Command;
import orion.orionexceptions.FileInitializationException;
import orion.orionexceptions.OrionException;
import orion.parser.Parser;
import orion.storage.Storage;
import orion.task.DeadlineDetails;
import orion.task.EventDetails;
import orion.task.Task;
import orion.tasklist.TaskList;
import orion.ui.MainWindow;

/**
 * The main class for the Orion application.
 *
 * <p>
 * This class is responsible for setting up the UI and the TaskList.
 * </p>
 */
public class Orion extends Application {
    private TaskList taskList;
    private Parser parser;

    /**
     * Creates a new Orion application instance.
     *
     * <p>
     * Initializes the TaskList and Parser.
     * </p>
     */
    public Orion() {
        try {
            Storage storage = new Storage();
            taskList = new TaskList(storage);
            parser = new Parser();
        } catch (FileInitializationException e) {
            System.out.println("Failed to initialize TaskList: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Starts the Orion application.
     *
     * <p>
     * Loads the main window from an FXML file and sets up the UI.
     * </p>
     *
     * @param stage the primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Orion.class.getResource("/view/MainWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setOrion(this);

            stage.setTitle("Orion Task Manager");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the Orion application.
     *
     * <p>
     * This is the entry point for the application. It simply calls the
     * {@link Application#launch(String[])} method to start the application.
     * </p>
     *
     * @param args the command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Handles user input and returns a response string.
     *
     * <p>
     * This method takes a string input from the user and processes it as a
     * command. It returns a response string based on the command and its
     * arguments. If the command is invalid, it returns an error message.
     * </p>
     *
     * @param input the user input string.
     * @return a response string based on the command.
     */
    public String getResponse(String input) {
        try {
            String[] parts = input.split(" ", 2);
            Command command = Command.fromString(parts[0]);

            // prettier-ignore
            return switch (command) {
            case LIST -> handleList(parts);
            case MARK -> handleMark(parts);
            case UNMARK -> handleUnmark(parts);
            case TODO -> handleTodo(parts);
            case EVENT -> handleEvent(parts);
            case DEADLINE -> handleDeadline(parts);
            case DELETE -> handleDelete(parts);
            case FIND -> handleFind(parts);
            case BYE -> "Goodbye!";
            default -> "Unknown command: " + parts[0];
            };
        } catch (OrionException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Handles the LIST command.
     *
     * <p>
     * This method takes the user input for the LIST command and returns a string
     * containing all the tasks in the task list. The tasks are numbered from 1
     * to the size of the list.
     * </p>
     *
     * @param parts the user input string split into parts.
     * @return a string containing all the tasks in the task list.
     * @throws OrionException if the command format is invalid.
     */
    private String handleList(String... parts) throws OrionException {
        parser.validateListCommand(parts);
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            response.append(i + 1).append(". ").append(taskList.loadTasksFromFile().get(i)).append("\n");
        }
        return response.toString();
    }

    /**
     * Handles the MARK command.
     *
     * <p>
     * This method takes the parts of the MARK command and marks the task at the
     * given index as done.
     * </p>
     *
     * @param parts the parts of the MARK command
     * @return a string indicating that the task was marked as done
     * @throws OrionException if there is an error with the command or its
     *                        arguments
     */

    private String handleMark(String... parts) throws OrionException {
        int index = parser.validateMarkAndUnMarkCommand(taskList, parts);
        Task task = taskList.markAsDone(index);
        return "Marked task as done: " + task;
    }

    /**
     * Handles the UNMARK command.
     *
     * <p>
     * This method takes the parts of the UNMARK command and unmarks the task at
     * the given index as done.
     * </p>
     *
     * @param parts the parts of the UNMARK command
     * @return a string indicating that the task was unmarked as done
     * @throws OrionException if there is an error with the command or its
     *                        arguments
     */
    private String handleUnmark(String... parts) throws OrionException {
        int index = parser.validateMarkAndUnMarkCommand(taskList, parts);
        Task task = taskList.unmarkAsDone(index);
        return "Unmarked task: " + task;
    }

    /**
     * Handles the TODO command.
     *
     * <p>
     * This method takes the parts of the TODO command and adds a new TODO task
     * with the given description.
     * </p>
     *
     * @param parts the parts of the TODO command
     * @return a string indicating that the task was added
     * @throws OrionException if there is an error with the command or its
     *                        arguments
     */
    private String handleTodo(String... parts) throws OrionException {
        String description = parser.validateTodoCommand(parts);
        Task task = taskList.addTodo(description);
        return "Added: " + task;
    }

    /**
     * Handles the EVENT command.
     *
     * <p>
     * This method takes the parts of the EVENT command and adds a new EVENT task
     * with the given description, start date and time, and end date and time.
     * </p>
     *
     * @param parts the parts of the EVENT command
     * @return a string indicating that the task was added
     * @throws OrionException if there is an error with the command or its
     *                        arguments
     */
    private String handleEvent(String... parts) throws OrionException {
        EventDetails details = parser.validateEventCommand(parts);
        Task task = taskList.addEvent(details);
        return "Added: " + task;
    }

    /**
     * Handles the DEADLINE command.
     *
     * <p>
     * This method takes the parts of the DEADLINE command and adds a new DEADLINE
     * task with the given description and due date and time.
     * </p>
     *
     * @param parts the parts of the DEADLINE command
     * @return a string indicating that the task was added
     * @throws OrionException if there is an error with the command or its
     *                        arguments
     */
    private String handleDeadline(String... parts) throws OrionException {
        DeadlineDetails details = parser.validateDeadlineCommand(parts);
        Task task = taskList.addDeadline(details);
        return "Added: " + task;
    }

    /**
     * Handles the DELETE command.
     *
     * <p>
     * This method takes the parts of the DELETE command and deletes the task
     * at the given index.
     * </p>
     *
     * @param parts the parts of the DELETE command
     * @return a string indicating that the task was deleted
     * @throws OrionException if there is an error with the command or its
     *                        arguments
     */
    private String handleDelete(String... parts) throws OrionException {
        int index = parser.validateDeleteCommand(taskList, parts);
        Task task = taskList.deleteTask(index);
        return "Deleted: " + task;
    }

    /**
     * Handles the FIND command.
     *
     * <p>
     * This method takes the parts of the FIND command and searches for all
     * tasks in the task list that match the given keyword.
     * </p>
     *
     * @param parts the parts of the FIND command
     * @return a string listing all matching tasks
     * @throws OrionException if there is an error with the command or its
     *                        arguments
     */
    private String handleFind(String... parts) throws OrionException {
        String keyword = parser.validateFindCommand(parts);
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : taskList.findTasks(keyword)) {
            response.append(task).append("\n");
        }
        return response.toString();
    }
}
