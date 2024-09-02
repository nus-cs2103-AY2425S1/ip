package optimus;

import java.util.ArrayList;

import javafx.application.Platform;

// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
/**
 * Handles interactions with the user including displaying messages and reading input.
 */
public class Ui {
    private MainWindow mainWindow;

    public Ui(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    // Method to show messages to the user in the chatbox
    // Suggested by ChatGPT to show System.out.println messages on the GUI instead
    public void showToUser(String message) {
        Platform.runLater(() -> mainWindow.showMessage(message));
    }

    /**
     * Greets the user and provides instructions for using Optimus.
     */
    public void greetUser() {
        String greeting = "Hello! I'm Optimus.\n"
                + "What can I do for you?\n"
                + "You need to start your input with either todo, deadline, or event.\n"
                + "For example:\n"
                + "todo borrow book\n"
                + "deadline return book /by 2019-12-02\n"
                + "event project meeting /from 2019-12-02 /to 2019-12-03\n"
                + "You can also do e.g.\n"
                + "mark 2\n"
                + "delete 1\n"
                + "list";
        showToUser(greeting);
    }

    /**
     * Displays example commands for the user.
     */
    public void showExampleCommand() {
        showToUser("You need to start your input with either todo, deadline, or event.\n"
                + "For example:\n"
                + "todo borrow book\n"
                + "deadline return book /by 2019-12-02\n"
                + "event project meeting /from 2019-12-02 /to 2019-12-03\n"
                + "You can also do e.g.\n"
                + "mark 2\n"
                + "delete 1\n"
                + "list");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void sayBye() {
        showToUser("Bye. Hope to see you again soon!\n");
    }

    /**
     * Lists all tasks in the TaskList.
     *
     * @param taskList The TaskList object containing the tasks to be listed.
     */
    public void listTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks = listOfTasks + "\n" + (i + 1) + "." + tasks.get(i).toString();
        }
        showToUser(listOfTasks);
    }

    /**
     * Finds and displays tasks from the task list that contain the specified keyword.
     *
     * @param userInput The raw input from the user, which includes the "find" command and the keyword.
     * @param taskList The TaskList object containing the list of tasks to search through.
     */
    public void findTasks(String userInput, TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        String[] command = Parser.parseCommand(userInput);
        String keyword = command[1];
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                result = result + "\n" + (i + 1) + "." + tasks.get(i).toString();
            }
        }
        showToUser("Here are the matching tasks in your list:\n" + result);
    }
}
