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

public class Orion extends Application {
    private TaskList taskList;
    private Parser parser;

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

    public static void main(String[] args) {
        launch(args);
    }

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

    private String handleList(String... parts) throws OrionException {
        parser.validateListCommand(parts);
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            response.append(i + 1).append(". ").append(taskList.loadTasksFromFile().get(i)).append("\n");
        }
        return response.toString();
    }

    private String handleMark(String... parts) throws OrionException {
        int index = parser.validateMarkAndUnMarkCommand(taskList, parts);
        Task task = taskList.markAsDone(index);
        return "Marked task as done: " + task;
    }

    private String handleUnmark(String... parts) throws OrionException {
        int index = parser.validateMarkAndUnMarkCommand(taskList, parts);
        Task task = taskList.unmarkAsDone(index);
        return "Unmarked task: " + task;
    }

    private String handleTodo(String... parts) throws OrionException {
        String description = parser.validateTodoCommand(parts);
        Task task = taskList.addTodo(description);
        return "Added: " + task;
    }

    private String handleEvent(String... parts) throws OrionException {
        EventDetails details = parser.validateEventCommand(parts);
        Task task = taskList.addEvent(details);
        return "Added: " + task;
    }

    private String handleDeadline(String... parts) throws OrionException {
        DeadlineDetails details = parser.validateDeadlineCommand(parts);
        Task task = taskList.addDeadline(details);
        return "Added: " + task;
    }

    private String handleDelete(String... parts) throws OrionException {
        int index = parser.validateDeleteCommand(taskList, parts);
        Task task = taskList.deleteTask(index);
        return "Deleted: " + task;
    }

    private String handleFind(String... parts) throws OrionException {
        String keyword = parser.validateFindCommand(parts);
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : taskList.findTasks(keyword)) {
            response.append(task).append("\n");
        }
        return response.toString();
    }
}
