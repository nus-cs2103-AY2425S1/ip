package orion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import orion.commands.Command;
import orion.orionExceptions.FileInitializationException;
import orion.orionExceptions.OrionException;
import orion.parser.Parser;
import orion.storage.Storage;
import orion.task.DeadlineDetails;
import orion.task.EventDetails;
import orion.task.Task;
import orion.taskList.TaskList;
import orion.ui.MainWindow;

import java.io.IOException;

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

            switch (command) {
                case LIST:
                    return handleList(parts);
                case MARK:
                    return handleMark(parts);
                case UNMARK:
                    return handleUnmark(parts);
                case TODO:
                    return handleTodo(parts);
                case EVENT:
                    return handleEvent(parts);
                case DEADLINE:
                    return handleDeadline(parts);
                case DELETE:
                    return handleDelete(parts);
                case FIND:
                    return handleFind(parts);
                case BYE:
                    return "Goodbye!";
                case UNKNOWN:
                default:
                    return "Unknown command: " + parts[0];
            }
        } catch (OrionException e) {
            return "Error: " + e.getMessage();
        }
    }

    private String handleList(String[] parts) throws OrionException {
        parser.validateListCommand(parts);
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            response.append(i + 1).append(". ").append(taskList.loadTasksFromFile().get(i)).append("\n");
        }
        return response.toString();
    }

    private String handleMark(String[] parts) throws OrionException {
        int index = parser.validateMarkAndUnMarkCommand(parts, taskList);
        Task task = taskList.markAsDone(index);
        return "Marked task as done: " + task;
    }

    private String handleUnmark(String[] parts) throws OrionException {
        int index = parser.validateMarkAndUnMarkCommand(parts, taskList);
        Task task = taskList.unmarkAsDone(index);
        return "Unmarked task: " + task;
    }

    private String handleTodo(String[] parts) throws OrionException {
        String description = parser.validateTodoCommand(parts);
        Task task = taskList.addTodo(description);
        return "Added: " + task;
    }

    private String handleEvent(String[] parts) throws OrionException {
        EventDetails details = parser.validateEventCommand(parts);
        Task task = taskList.addEvent(details);
        return "Added: " + task;
    }

    private String handleDeadline(String[] parts) throws OrionException {
        DeadlineDetails details = parser.validateDeadlineCommand(parts);
        Task task = taskList.addDeadline(details);
        return "Added: " + task;
    }

    private String handleDelete(String[] parts) throws OrionException {
        int index = parser.validateDeleteCommand(parts, taskList);
        Task task = taskList.deleteTask(index);
        return "Deleted: " + task;
    }

    private String handleFind(String[] parts) throws OrionException {
        String keyword = parser.validateFindCommand(parts);
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : taskList.findTasks(keyword)) {
            response.append(task).append("\n");
        }
        return response.toString();
    }
}
