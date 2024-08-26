package optimus;

import java.util.ArrayList;

// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
/**
 * Handles interactions with the user including displaying messages and reading input.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Greets the user and provides instructions for using the chatbot.
     */
    public void greetUser() {
        String greeting = "Hello! I'm Optimus.\n" +
                "What can I do for you?\n";
        System.out.println(greeting);
        System.out.println("You need to start your input with either todo, deadline, or event.\n" +
                "For example:\n" +
                "todo borrow book\n" +
                "deadline return book /by 2019-12-02\n" +
                "event project meeting /from 2019-12-02 /to 2019-12-03\n");
        System.out.println("You can also do e.g.\n" + "mark 2\n" + "delete 1\n" + "list");
    }

    /**
     * Displays example commands for the user.
     */
    public void showExampleCommand() {
        System.out.println("You need to start your input with either todo, deadline, or event.\n" +
                "For example:\n" +
                "todo borrow book\n" +
                "deadline return book /by 2019-12-02\n" +
                "event project meeting /from 2019-12-02 /to 2019-12-03");
        System.out.println("You can also do e.g.\n" + "mark 2\n" + "delete 1\n" + "list");
    }

    /**
     * Displays a farewell message to the user.
     */
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Lists all tasks in the TaskList.
     *
     * @param taskList The TaskList object containing the tasks to be listed.
     */
    public void listTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
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
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        }
    }
}
