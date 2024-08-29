package ui;

import task.TaskList;
import task.Task;
import storage.Storage;
import parser.Parser;

import java.util.Scanner;

/**
 * Prompts users for input and also to output responses to them.
 */
public class Ui {

    private static Scanner input = new Scanner(System.in);

    /**
     * Greets the user when the chatbot just starts and ask them for an input.
     */
    public static void greet() {

        System.out.println("Hello! I'm Tako! What can I do for you?\n");

        promptInput();
    }

    /**
     * Says goodbye to the user
     */
    public static void exit() {
        System.out.println("Bye! Have a nice day!");
    }

    /**
     * Prints out the list of tasks that the chatbot is keeping track of and
     * ask them for input after that.
     */
    public static void printList() {
        System.out.println("Here are your list of tasks:");
        for (int i = 0; i < TaskList.length(); i++) {
            System.out.println(TaskList.listTask(i));
        }
        promptInput();
    }

    /**
     * Prints out the message when the user added a task to the list and
     * ask them for input after that.
     *
     * @param task task that is being added to the list.
     */
    public static void addTaskMessage(Task task) {
        System.out.println("Task Received! I've added this task:\n" +
                          task.toString() + "\n" +
                          "Now, you have " + TaskList.length() + " tasks in your list!");
        Storage.store(TaskList.getAllTask());
        promptInput();
    }

    /**
     * Prints out the message when the user mark a task on the list and
     * ask them for input after that.
     *
     * @param task task that is being marked on the list.
     */
    public static void markTaskMessage(Task task) {
        System.out.println("Task has been marked as complete! \n" +
                           task.toString());
        Storage.store(TaskList.getAllTask());
        promptInput();
    }

    /**
     * Prints out the message when the user unmark a task on the list and
     * ask them for input after that.
     *
     * @param task task that is being unmark on the list.
     */
    public static void unmarkTaskMessage(Task task) {
        System.out.println("Task has been marked as incomplete! \n" +
                           task.toString());
        Storage.store(TaskList.getAllTask());
        promptInput();
    }

    /**
     * Prints out the message when the user delete a task on the list and
     * ask them for input after that.
     *
     * @param task task that is being deleted on the list.
     */
    public static void deleteTaskMessage(Task task) {
        System.out.println("Task has been deleted!\n" +
                           task.toString() + "\n" +
                           "Now, you have " + TaskList.length() + " tasks in your list!");
        Storage.store(TaskList.getAllTask());
        promptInput();
    }

    /**
     * Asks the user for the next input to the chatbot.
     */
    public static void promptInput() {
        String command = input.nextLine();

        Parser.parse(command);
    }
}
