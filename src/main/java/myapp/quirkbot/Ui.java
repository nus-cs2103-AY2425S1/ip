package myapp.quirkbot;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import quirkbot.gui.MainWindow;

/**
 * Main class which represents the JavaFX GUI for the chatbot.
 */
public class Ui extends Application {
    private static final String HOME = System.getProperty("user.home");
    private static final String DIRECTORY_PATH = HOME + "/Documents/";
    private static final String FILE_PATH = DIRECTORY_PATH + "TaskInfo.txt";

    private TaskList taskList;
    private Storage storage;
    private String commandType = "";

    /**
     * Constructs a new GUI for the user.
     * Initializes the GUI application and greets the user during startup.
     *
     * @param stage sets up the GUI application.
     */
    @Override
    public void start(Stage stage) {
        taskList = new TaskList();
        storage = initStorage();
        if (storage == null) {
            System.out.println("Oops! I couldn't set up storage. Let's try again later!");
            return;
        }

        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setBuddy(this);
            storage.loadTasks(taskList.getTasks());
            stage.setTitle("QuirkBot - Your Friendly Assistant");
            Image icon = new Image(this.getClass().getResourceAsStream("/images/QuirkBot.png"));
            stage.getIcons().add(icon);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the storage by creating necessary directories and files if they do not exist.
     *
     * @return the Storage instance if initialization is successful, otherwise null.
     */
    public Storage initStorage() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists() && !directory.mkdirs()) {
            System.out.println("Oh no! I couldn’t create the directory. Maybe try again later?");
            return null;
        }

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.out.println("Oops! Couldn’t create the file. What a pickle!");
                    return null;
                }
            } catch (IOException e) {
                System.out.println("A glitch occurred while creating the file. My bad!");
                e.printStackTrace();
                return null;
            }
        }

        return new Storage(FILE_PATH);
    }

    /**
     * Processes the user's command and returns the relevant response.
     *
     * @param command instruction to the chatbot.
     * @return the response to the command.
     */
    public String processCommand(String command) {
        assert command != null : "Oops! The command shouldn’t be null.";

        String response;
        if (command.equals("bye")) {
            response = "Farewell! I’ll be here when you need me. 🌟";
            handleExit();
        } else if (command.equals("list")) {
            response = getTaskListMessage();
        } else if (command.equals("command")) {
            response = showListOfCommands();
        } else if (command.startsWith("delete")) {
            commandType = "DeleteCommand";
            response = handleDeleteTask(command);
        } else if (command.startsWith("mark")) {
            commandType = "MarkCommand";
            response = handleMarkTask(command);
        } else if (command.startsWith("unmark")) {
            response = handleUnmarkTask(command);
        } else if (command.startsWith("find")) {
            response = handleFindTask(command);
        } else {
            commandType = "AddCommand";
            response = handleAddTask(command);
        }
        return response;
    }

    /**
     * Checks the type of the command entered by the user.
     *
     * @return the command type.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Presents all the available commands for the user.
     *
     * @return the list of commands.
     */
    public String showListOfCommands() {
        return " List of Commands:\n"
                + "1. find search_keyword \n"
                + "2. todo task_description\n"
                + "3. deadline task_description /by dd/MM/YYYY HHmm\n"
                + "4. event task_description /from dd/MM/YYYY HHmm /to dd/MM/YYYY HHmm\n"
                + "5. delete task_number\n"
                + "6. mark task_number\n"
                + "7. unmark task_number\n"
                + "8. list\n"
                + "9. bye\n";
    }

    /**
     * Removes task and shows deleted task.
     *
     * @param command entered by the user in the command box.
     * @return the message of the deleted task.
     */
    public String handleDeleteTask(String command) {
        assert command.startsWith("delete") : "Oh dear! The command should start with 'delete'.";

        try {
            int taskIndex = parseTaskIndex(command);
            assert taskIndex >= 0 && taskIndex < taskList.size() : "The task index seems to be out of bounds!";
            Task removedTask = taskList.deleteTask(taskIndex);
            return showTaskRemoved(removedTask);
        } catch (NumberFormatException e) {
            return "Oopsie! That’s not a valid task index.";
        }
    }

    /**
     * Marks task as done and shows the marked task to user.
     *
     * @param command entered by the user in the command box.
     * @return the message of the marked task.
     */
    public String handleMarkTask(String command) {
        assert command.startsWith("mark") : "Oops! The command should start with 'mark'.";

        try {
            int taskIndex = parseTaskIndex(command);
            assert taskIndex >= 0 && taskIndex < taskList.size() : "Hmm, that task index is not valid.";
            Task currentTask = taskList.getTask(taskIndex);
            return showTaskMarked(currentTask);
        } catch (NumberFormatException e) {
            return "Oh dear, that's not a valid task index.";
        }
    }

    /**
     * Marks task as not done and shows the unmarked task to user.
     *
     * @param command entered by the user in the command box.
     * @return the message of the unmarked task.
     */
    public String handleUnmarkTask(String command) {
        assert command.startsWith("unmark") : "Oops! The command should start with 'unmark'.";

        try {
            int taskIndex = parseTaskIndex(command);
            assert taskIndex >= 0 && taskIndex < taskList.size() : "The task index seems a bit off.";
            Task currentTask = taskList.getTask(taskIndex);
            return showTaskUnmarked(currentTask);
        } catch (NumberFormatException e) {
            return "Oopsie daisy! That index doesn’t look right.";
        }
    }

    /**
     * Finds specific task(s) from the task list based on the user keyword.
     *
     * @param command entered by the user in the command box.
     * @return the message of the task to find.
     */
    public String handleFindTask(String command) {
        assert command.startsWith("find") : "Oops! The command should start with 'find'.";

        String keyword = command.substring(5).trim();
        if (keyword.isEmpty()) {
            return "Oops! You forgot to enter your search keyword. 😅";
        }

        List<Task> searchResults = taskList.searchTasks(keyword);
        return showSearchResults(searchResults);
    }

    /**
     * Adds desired task to the task list and shows added task to user.
     *
     * @param command entered by the user in the command box.
     * @return the message of the added task.
     */
    public String handleAddTask(String command) {
        assert command != null && !command.trim().isEmpty() : "Oh no! The command can’t be empty.";

        Task currentTask = Parser.parseCommand(command);
        if (currentTask == null) {
            return showErrorMessage(command);
        }

        taskList.addTask(currentTask);
        return showTaskAdded(currentTask, taskList.size());
    }

    /**
     * Parses the task index from the command string.
     *
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
     * Shows an error message to user based on the invalid command.
     *
     * @param command entered by the user in the command box.
     * @return the formatted error message.
     */
    public String showErrorMessage(String command) {
        assert command != null : "The command shouldn’t be null!";

        if (command.startsWith("todo")) {
            return "Oops! The todo task description can’t be empty. Give me something to work with!";
        } else if (command.startsWith("deadline")) {
            return "Oops! Your deadline task description is missing! Also, please use the dd/MM/yyyy HHmm format.";
        } else if (command.startsWith("event")) {
            return "Oops! Your event task description is missing! "
                    + "Also, use the dd/MM/yyyy HHmm format for the timings.";
        }

        return "Oops! That command doesn’t seem right.";
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
     *
     * @param task         added to the list.
     * @param tasklistSize updated number of tasks in the list.
     * @return the formatted message of the added task.
     */
    public String showTaskAdded(Task task, int tasklistSize) {
        assert task != null : "Task should not be null";
        assert tasklistSize > 0 : "Task list size should be greater than 0";
        return "Great! I’ve added this task: " + task.getDescription()
                + "\nYou now have " + tasklistSize + " tasks in your list. 🎉";
    }

    /**
     * Displays a message indicating that a task has been removed.
     * Shows the details of the removed task and the updated size of the task list.
     *
     * @param task removed from the list.
     * @return the formatted message of the removed task.
     */
    public String showTaskRemoved(Task task) {
        assert task != null : "Task should not be null";
        return "Done! I’ve removed this task: " + task.getDescription()
                + "\nNow you have " + taskList.size() + " tasks left. 📉";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     * Marks the provided task as done and shows its updated status.
     *
     * @param task marked as done.
     * @return the formatted message of the marked task.
     */
    public String showTaskMarked(Task task) {
        assert task != null : "Task should not be null";
        task.markDone();
        return "Hooray! I’ve marked this task as done: " + task.getDescription();
    }

    /**
     * Displays a message indicating that a task has been marked as not done yet.
     * Marks the provided task as not done and shows its updated status.
     *
     * @param task marked as not done.
     * @return the formatted message of the unmarked task.
     */
    public String showTaskUnmarked(Task task) {
        assert task != null : "Task should not be null";
        task.markUndone();
        return "No worries! I’ve unmarked this task as not done yet: " + task.getDescription();
    }

    /**
     * Displays the list of tasks to the user.
     * Shows all tasks in the provided TaskList with their corresponding index.
     *
     * @return the formatted list of tasks.
     */
    public String getTaskListMessage() {
        assert taskList != null : "Task List should not be null";

        List<Task> tasks = taskList.getTasks();
        String taskListMessage = tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task)
                .reduce("Here are the fabulous tasks in your list:\n", (acc, taskStr) -> acc + taskStr + "\n");
        return taskListMessage;
    }

    /**
     * Filters out the tasks from the list which match the user keyword.
     * Displays all the relevant search results from the task list.
     *
     * @param tasks is the task list to be searched.
     * @return the formatted search results.
     */
    public String showSearchResults(List<Task> tasks) {
        assert tasks != null : "Task List should not be null";
        if (tasks.isEmpty()) {
            return "No tasks found that match your search. Maybe try a different keyword?";
        }

        String searchResults = tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task)
                .reduce("Here are the search results for you:\n", (acc, taskStr) -> acc + taskStr + "\n");
        return searchResults;
    }
}