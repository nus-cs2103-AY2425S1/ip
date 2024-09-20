package Jard;

import java.util.List;
import java.util.ArrayList;

/**
 * The Jard class represents the main application that interacts with the user.
 * It handles the core functionality of the chatbot, including task management,
 * user input processing, and file storage.
 * Code was created with the help of ChatGPT.
 */
public class Jard {
    private Storage storage;
    private List<Task> tasks;
    private Ui ui;

    /**
     * Constructs a Jard instance with the specified file path for task storage.
     *
     * @param filePath The path of the file where tasks will be loaded from and saved to.
     */
    public Jard(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.loadTasks();
    }

    /**
     * Finds tasks that contain the word after find.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A list of tasks that match the keyword.
     */
    private List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Processes the user command and returns a response to be displayed in the GUI.
     *
     * @param input The user command.
     * @return The response to be displayed.
     */
    public String getResponse(String input) {
        String[] inputParts = Parser.parseCommand(input);
        String command = inputParts[0];
        String response;

        try {
            switch (command) {
                case "bye":
                    response = ui.showBye();
                    break;
                case "list":
                    response = ui.showTaskList(tasks);
                    break;
                case "mark":
                    int markIndex = Parser.parseTaskIndex(inputParts[1], tasks.size());
                    Task markTask = tasks.get(markIndex);
                    markTask.markAsDone();
                    response = ui.showMarkTask(markTask);
                    break;
                case "unmark":
                    int unmarkIndex = Parser.parseTaskIndex(inputParts[1], tasks.size());
                    Task unmarkTask = tasks.get(unmarkIndex);
                    unmarkTask.markAsNotDone();
                    response = ui.showUnmarkTask(unmarkTask);
                    break;
                case "delete":
                    int deleteIndex = Parser.parseTaskIndex(inputParts[1], tasks.size());
                    Task deleteTask = tasks.remove(deleteIndex);
                    response = ui.showDeleteTask(deleteTask, tasks.size());
                    break;
                case "find":
                    String keyword = inputParts[1];
                    List<Task> matchingTasks = findTasks(keyword);
                    response = ui.showFindResults(matchingTasks);
                    break;
                default:
                    Task newTask = Parser.parseTask(input);
                    tasks.add(newTask);
                    response = ui.showAddTask(newTask, tasks.size());
                    break;
            }
            storage.saveTasks(tasks);
        } catch (JardException e) {
            response = ui.showError(e.getMessage());
        }
        return response;
    }

    public String getWelcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * The main method serves as the entry point for the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
    }
}
