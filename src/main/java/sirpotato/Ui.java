package sirpotato;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that handles the interactions with the users
 * Reads their input
 * Handles responses to the user
 */
public class Ui {

    private String logo;
    private Scanner scanner;
    private final String HORIZONTAL_LINE = "___________________________ \n";
    private final String INDENT = "   ";

    /**
     * Initialises the UI for the user
     * Sets the logo and initialises the scanner
     */
    public Ui() {
        this.logo = "Sir Potato";
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the user's next command to the chatbot.
     */
    public String readCommand() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Displays & returns the welcome message when the user first starts the chat
     * 
     * @return Welcome message
     */
    public String displayWelcomeMessage() {
        String response = "Hello from\n" + logo + 
                "What can I do ya for?\n" + HORIZONTAL_LINE;
        System.out.println(response);
        return response;
    }

    /**
     * Displays & returns the bye message when the user types 'bye'.
     * 
     * @return Bye message
     */
    public String displayByeMessage() {
        String response = "Bye mate, see you around.";
        System.out.println(response);
        return response;
    }

    /**
     * Displays & returns the message that a task has been added to the list
     * 
     * @param task the task that is being added to the toDoList.
     * @param toDoList the toDoList the task is being added to.
     * @return Added task message
     */
    public String displayAddedTask(Task task, TaskList tasks) {
        String response = HORIZONTAL_LINE + 
                INDENT + "Got it. I've added this task for ya." + 
                INDENT + task + 
                INDENT + "Now you've got " + tasks.getSizeOfList() + " tasks in the list mate" + 
                HORIZONTAL_LINE;
        System.out.println(response);
        return response;
    }

    /**
     * Prints & returns out the deletion message 
     * 
     * @param task Task being deleted
     * @param toDoList The to-do list being deleted from
     * @return Deletion of task message
     */
    public String displayDeletionMessage(Task task, TaskList tasks) {
        String response = HORIZONTAL_LINE +
                "Gotcha mate, I've deleted the following task: " + 
                task + 
                "Now you've got " + (tasks.getSizeOfList()) + " tasks left." + 
                HORIZONTAL_LINE;
        System.out.println(response);
        return response;
    }

    /**
     * Prints & returns the tasks in the current tasklist 
     * 
     * @param tasks The TaskList containing the current tasks
     * @return the formatted list of tasks
     */
    public String listTasks(TaskList tasks) {
        String response = HORIZONTAL_LINE + 
                INDENT + "Here are the tasks in your list:" + 
                INDENT + "List: ";

        for (int i = 0; i < tasks.getList().size(); i++) {
            response = response + INDENT + (i+1) + ". " + tasks.getList().get(i);
        }
        response = response + HORIZONTAL_LINE + "\n";
        System.out.println(response);
        return response;
    }

    /**
     * Prints & returns the sorted task list
     * 
     * @param tasks The TaskList containing the current tasks
     * @return the formatted list of tasks
     */
    public String displaySortedTasks(TaskList tasks) {
        String response = "Here is the sorted task list: \n";

        for (int i = 0; i < tasks.getList().size(); i++) {
            response = response + INDENT + (i+1) + ". " + tasks.getList().get(i);
        }
        response = response + HORIZONTAL_LINE + "\n";
        System.out.println(response);
        return response;
    }

    /**
     * When a user marks an item complete, this is the message that will be displayed
     * Prints and returns the marked item
     * 
     * @param itemNumber the index number(starts at 1) of the item to be deleted
     * @param toDoList the TaskList's arrayList containing the current to-do list.
     * @return the marked item message
     */
    public String displayMarkedItem(int itemNumber, TaskList tasks) {
        String response = HORIZONTAL_LINE + 
                INDENT + "Good on ya! I've marked it done:" + 
                INDENT + INDENT + tasks.getTask(itemNumber) + 
                HORIZONTAL_LINE;
        System.out.println(response);
        return response;
    }

    /**
     * When a user unmarks an item to incomplete, this is the message that will be displayed
     * Prints and returns unmarked item  
     * 
     * @param itemNumber the index number(starts at 1) of the item to be deleted
     * @param toDoList the TaskList's arrayList containing the current to-do list.
     * @return the unmarked item message
     */
    public String displayUnmarkedItem(int itemNumber, TaskList tasks) {
        String response = HORIZONTAL_LINE + 
                INDENT + "What's happened here mate? I've unmarked it for ya." + 
                INDENT + INDENT + tasks.getTask(itemNumber) + 
                HORIZONTAL_LINE;
        System.out.println(response);
        return response;
    }

    /**
     * Formats the chatbot's response by sandwiching it between a dashed line
     * And automatically indents the response then returns it and prints it
     * 
     * @param input the text that the chatbot wishes to format
     * @return the response sandwiched between horizontal lines
     */
    public String respond(String input) {
        String response = HORIZONTAL_LINE + 
                INDENT + input +
                HORIZONTAL_LINE;
        System.out.println(response);
        return response;
    }

    /**
     * Prints & returns all the tasks in the tasklist that match the search string 
     * 
     * @param tasks The Tasklist in which we wish to search
     * @param searchString The string we wish to search for
     * @return the formatted list of tasks that match the search
     */
    public String findTasks(TaskList tasks, String searchString) {
        String response = HORIZONTAL_LINE + 
                INDENT + "Here are the matching tasks found:";

        for (int i = 0; i < tasks.getList().size(); i++) {
            if (tasks.getList().get(i).containsString(searchString)) {
                response = response + INDENT + (i+1) + ". " + tasks.getList().get(i);
            }
        }
        response = response + HORIZONTAL_LINE + "\n";
        System.out.println(response);
        return response;
    }

}