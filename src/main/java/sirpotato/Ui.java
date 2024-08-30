package sirpotato;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private String logo;
    private Scanner scanner;
    private final String HORIZONTAL_LINE = "___________________________ \n";
    private final String INDENT = "   ";

    public Ui() {
        this.logo = "Sir Potato";
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Displays the welcome message when the user first starts the chat
     */
    public void displayWelcomeMessage() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do ya for?\n" + HORIZONTAL_LINE);
    }

    /**
     * Displays the bye message when the user types 'bye'.
     */
    public void displayByeMessage() {
        System.out.println("Bye mate, see you around.");
    }

    /**
     * Displays the message that a task has been added to the list
     * @param task the task that is being added to the toDoList.
     * @param toDoList the toDoList the task is being added to.
     */
    public void displayAddedTask(Task task, ArrayList<Task> toDoList) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENT + "Got it. I've added this task for ya.");
        System.out.println(INDENT + task);
        System.out.println(INDENT + "Now you've got " + toDoList.size() + " tasks in the list mate");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints out the deletion message 
     * @param task Task being deleted
     * @param toDoList The to-do list being deleted from
     */
    public void displayDeletionMessage(Task task, ArrayList<Task> toDoList) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Gotcha mate, I've deleted the following task: ");
        System.out.println(task);
        System.out.println("Now you've got " + (toDoList.size() - 1) + " tasks left.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void listTasks(TaskList tasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENT + "Here are the tasks in your list:");
        System.out.println(INDENT + "List: ");

        for (int i = 0; i < tasks.getList().size(); i++) {
            System.out.println(INDENT + (i+1) + ". " + tasks.getList().get(i));
        }
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    public void displayMarkedItem(int itemNumber, ArrayList<Task> toDoList) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENT + "Good on ya! I've marked it done:");
        System.out.println(INDENT + INDENT + toDoList.get(itemNumber));
        System.out.println(HORIZONTAL_LINE);
    }

    public void displayUnmarkedItem(int itemNumber, ArrayList<Task> toDoList) {
        System.out.println(INDENT + "What's happened here mate? I've unmarked it for ya.");
        System.out.println(INDENT + INDENT + toDoList.get(itemNumber));
        System.out.println(HORIZONTAL_LINE);
    }

    public void respond(String response) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENT + response);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints all the tasks in the tasklist that match the search string 
     * 
     * @param tasks The Tasklist in which we wish to search
     * @param searchString The string we wish to search for
     */
    public void findTasks(TaskList tasks, String searchString) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENT + "Here are the matching tasks found:");

        for (int i = 0; i < tasks.getList().size(); i++) {
            if (tasks.getList().get(i).containsString(searchString)) {
                System.out.println(INDENT + (i+1) + ". " + tasks.getList().get(i));
            }
        }
        System.out.println(HORIZONTAL_LINE + "\n");
    }

}