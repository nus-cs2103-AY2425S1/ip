package myapp.helperbuddy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main class which represents the JavaFX GUI for the chatbot
 */
public class Ui extends Application {
    private static final String HOME = System.getProperty("user.home");
    private static final String DIRECTORY_PATH = HOME + "/Documents/";
    private static final String FILE_PATH = DIRECTORY_PATH + "TaskInfo.txt";
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a new GUI for the user
     * Initializes the GUI application and greets the user during startup
     *
     * @param stage sets up the GUI application
     */
    @Override
    public void start(Stage stage) {
        taskList = new TaskList();
        initStorage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBuddy(this);
            stage.setTitle("Hello! I'm YourHelperBuddy.");
            storage.loadTasks(taskList.getTasks());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new file and directory if they do not exist
     */
    public void initStorage() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists() && !directory.mkdirs()) {
            System.out.println("Failed to create directory: " + DIRECTORY_PATH);
            return;
        }
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.out.println("Failed to create file: " + FILE_PATH);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
        }
        storage = new Storage(FILE_PATH);
    }


    /**
     * Processes the user's command and returns the relevant response.
     *
     * @param command instruction to the chatbot
     * @return the bot's response to the command
     */
    public String processCommand(String command) {
        String response;
        if (command.equals("bye")) {
            response = "Goodbye. Take care and see you again!";
            handleExit();
        } else if (command.equals("list")) {
            response = getTaskListMessage();
        } else if (command.startsWith("delete")) {
            response = handleDeleteTask(command);
        } else if (command.startsWith("mark")) {
            response = handleMarkTask(command);
        } else if (command.startsWith("unmark")) {
            response = handleUnmarkTask(command);
        } else if (command.startsWith("find")) {
            response = handleFindTask(command);
        } else {
            response = handleAddTask(command);
        }
        return response;
    }

    /**
     * Removes task and shows deleted task
     *
     * @param command entered by the user in the command box
     * @return the formatted string to show deleted task
     */
    public String handleDeleteTask(String command) {
<<<<<<< HEAD
        assert command.startsWith("delete") : "Command should start with 'delete'";

        int taskIndex = parseTaskIndex(command);
        assert taskIndex >= 0 && taskIndex < taskList.size() : "Task index out of bounds";

        Task removedTask = taskList.deleteTask(taskIndex);
        return showTaskRemoved(removedTask);
=======
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            Task removedTask = taskList.deleteTask(taskIndex);
            return showTaskRemoved(removedTask);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Invalid task index.";
        }
>>>>>>> branch-A-CodeQuality
    }

    /**
     * Marks task as done and shows the marked task to user
     * @param command entered by the user in the command box
     * @return the formatted string to show marked task
     */
    public String handleMarkTask(String command) {
<<<<<<< HEAD
        assert command.startsWith("mark") : "Command should start with 'mark'";

        int taskIndex = parseTaskIndex(command);
        assert taskIndex >= 0 && taskIndex < taskList.size() : "Task index out of bounds";

        Task currentTask = taskList.getTask(taskIndex);
        return showTaskMarked(currentTask);
=======
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            Task currentTask = taskList.getTask(taskIndex);
            return showTaskMarked(currentTask);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Invalid task index.";
        }
>>>>>>> branch-A-CodeQuality
    }

    /**
     * Marks task as not done and shows the unmarked task to user
     * @param command entered by the user in the command box
     */
    public String handleUnmarkTask(String command) {
<<<<<<< HEAD
        assert command.startsWith("unmark") : "Command should start with 'unmark'";

        int taskIndex = parseTaskIndex(command);
        assert taskIndex >= 0 && taskIndex < taskList.size() : "Task index out of bounds";

        Task currentTask = taskList.getTask(taskIndex);
        return showTaskUnmarked(currentTask);
=======
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            Task currentTask = taskList.getTask(taskIndex);
            return showTaskUnmarked(currentTask);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Invalid task index.";
        }
>>>>>>> branch-A-CodeQuality
    }

    /**
     * Finds specific task(s) from the task list based on the user keyword
     *
     * @param command entered by the user in the command box
     */
    public String handleFindTask(String command) {
        assert command.startsWith("find") : "Command should start with 'find'";

        String keyword = command.substring(5).trim();
        if (keyword.isEmpty()) {
            return "Please enter your search keyword.";
        }
        List<Task> searchResults = taskList.searchTasks(keyword);
        return showSearchResults(searchResults);
    }

    /**
     * Adds desired task to the task list and shows added task to user
     *
     * @param command entered by the user in the command box
     */
    public String handleAddTask(String command) {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty";

        Task currentTask = Parser.parseCommand(command);
        if (currentTask != null) {
            taskList.addTask(currentTask);
            return showTaskAdded(currentTask, taskList.size());
        } else {
            return showErrorMessage(command);
        }
    }

    /**
<<<<<<< HEAD
     * Parses the task index from the command string.
     * @param command The command string containing the task index.
     * @return the parsed task index, or -1 if the index is invalid.
     */
    public int parseTaskIndex(String command) {
        try {
            return Integer.parseInt(command.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    /**
     * Shows an error message to user based on his invalid command.
     * @param command entered by the user in the command box.
     * @return the formatted error message.
=======
     * Shows an error message to user based on his invalid command
     *
     * @param command entered by the user in the command box
>>>>>>> branch-A-CodeQuality
     */
    public String showErrorMessage(String command) {
        assert command != null : "Command should not be null";
        if (command.startsWith("todo")) {
            return "Sorry! The todo task description cannot be empty.";
        } else if (command.startsWith("deadline")) {
            return "Sorry! The deadline task description cannot be empty.\n"
                    + "The deadline timing should be in dd/MM/yyyy HHmm format.";
        } else if (command.startsWith("event")) {
            return "Sorry! The event task description cannot be empty.\n"
                    + "The event from and to timings should be in dd/MM/yyyy HHmm format.";
        } else {
            return "Invalid command. Please use 'find', 'todo', 'deadline', 'event', 'delete',"
                    + " 'mark', 'unmark', 'list' or 'bye'. Thank you for understanding!";
        }
<<<<<<< HEAD

        return "Invalid command. Please use 'find', 'todo', 'deadline', 'event', 'delete',"
                    + " 'mark', 'unmark', 'list' or 'bye'. Thank you for understanding!";
=======
>>>>>>> branch-A-CodeQuality
    }

    /**
     * Displays a goodbye message to the user.
     * The application will exit 3 seconds after the message.
     */
    public void handleExit() {
        storage.saveTasks(taskList.getTasks());
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> Platform.exit());
        pause.play();
    }

    /**
     * Displays a message indicating that a task has been added.
     * Shows the details of the added task and the updated size of the task list.
     * @param task added to the list.
     * @param tasklistSize updated number of tasks in the list.
     */
    public String showTaskAdded(Task task, int tasklistSize) {
        assert task != null : "Task should not be null";
        assert tasklistSize > 0 : "Task list size should be greater than 0";
        return "Got it. I've added this task: " + task
                + "\nNow you have " + tasklistSize + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been removed.
     * Shows the details of the removed task and the updated size of the task list.
     * @param task removed from the list.
     */
    public String showTaskRemoved(Task task) {
        assert task != null : "Task should not be null";
        return "Noted. I've removed this task: " + task
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     * Marks the provided task as done and shows its updated status.
     * @param task marked as done.
     */
    public String showTaskMarked(Task task) {
        assert task != null : "Task should not be null";
        task.markDone();
        return "Nice! I've marked this task as done: " + task;
    }

    /**
     * Displays a message indicating that a task has been marked as not done yet.
     * Marks the provided task as not done and shows its updated status.
     * @param task marked as not done.
     */
    public String showTaskUnmarked(Task task) {
        assert task != null : "Task should not be null";
        task.markUndone();
        return "OK, I've marked this task as not done yet: " + task;
    }

    /**
     * Displays the list of tasks to the user.
     * Shows all tasks in the provided TaskList with their corresponding index.
     */
    public String getTaskListMessage() {
        assert taskList != null : "Task List should not be null";
        List<Task> tasks = taskList.getTasks();

        String taskListMessage = tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task)
                .reduce("Here are the tasks in your list:\n", (acc, taskStr) -> acc + taskStr + "\n");
        return taskListMessage;
    }

    /**
     * Filters out the tasks from the list which match the user keyword.
     * Displays all the relevant search results from the task list
     * @param tasks is the task list to be searched
     */
    public String showSearchResults(List<Task> tasks) {
        assert tasks != null : "Task List should not be null";
        if (tasks.isEmpty()) {
            return "No tasks found matching the search keyword.";
        } else {
            StringBuilder results = new StringBuilder("Search results:\n");
            int index = 0;
            for (Task task : tasks) {
                results.append(++index).append(". ").append(task).append("\n");
            }
            return results.toString();
        }
<<<<<<< HEAD

        String searchResults = tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task)
                .reduce("Search results:\n", (acc, taskStr) -> acc + taskStr + "\n");
        return searchResults;
=======
>>>>>>> branch-A-CodeQuality
    }
}