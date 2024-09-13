package ui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import tasks.Task;

/**
 * Class for Handling the printing of messages onto command line
 */
public class UI {
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String BOTNAME = "Chatterbox";


    private static final DateTimeFormatter PRINTDATEFORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    public UI() {

    }

    /**
     * Displays the default greeting when running the bot
     *
     */
    public void greeting() {
        System.out.println(String.format("""
____________________________________________________________
 Hello! I'm %s
 What can I do for you?""", BOTNAME));
    }

    /**
     * Displays the default goodbye message
     *
     */
    public void goodBye() {
        System.out.println(UI.getLineseperator());
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(UI.getLineseperator());
    }



    /**
     *  Returns the default line separator used
     *
     * @return a line separator
     */
    public static String getLineseperator() {
        return UI.LINE_SEPARATOR;
    }

    /**
     * Takes in the current List of Task objects and prints them in list format
     * @param userList is a List of Tasks
     *
     */
    public void displayList(ArrayList<Task> userList) {
        System.out.println(UI.getLineseperator());
        System.out.println("Current Tasks in List: ");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(String.format(i + 1 + ". " + "[%s][%s] %s",
                    userList.get(i).getTaskSymbol(),
                    userList.get(i).getStatus() ? "X" : " ",
                    userList.get(i).getDescription()));
        }

    }

    public void displayTaskDescription(Task task) {
        System.out.println(task.getDescription());
    }

    /**
     * Displays the text for marking task as done
     * @param task marked done
     */
    public void markMsg(Task task) {
        System.out.println(UI.getLineseperator());
        System.out.println("Marked Task as done");
        System.out.println(task.getDescription());
    }

    /**
     * Displays text for unmarking a task
     * @param task marked as undone
     */
    public void unmarkMsg(Task task) {
        System.out.println(UI.getLineseperator());
        System.out.println("Marked Task as undone");
        System.out.println(task.getDescription());
    }

    /**
     * Displays message for adding a task
     * @param type Type of task (todo, deadline ...)
     * @param size of tasks.TaskList object task is added to
     */
    public void addTaskMsg(String type, int size) {
        System.out.println(UI.getLineseperator());
        System.out.println(String.format("Added %s to Tasks", type));
        System.out.println(String.format("Currently %d Tasks in List", size));
    }

    /**
     * Displays message for deleting a task
     * @param task Task that is deleted
     * @param size size of the tasks.TaskList object task is removed from
     */
    public void delTaskMsg(Task task, int size) {
        System.out.println(UI.getLineseperator());
        System.out.println("Deleting Task: ");
        System.out.println(task.getDescription());
        System.out.println(String.format("%d tasks left", size));

    }

    /**
     * Displays the list of matching tasks from search parameter
     * @param matches ArrayList of matching tasks
     */
    public void displaySearch(ArrayList<Task> matches) {
        System.out.println(UI.getLineseperator());
        System.out.println("Displaying all matching tasks: ");
        for (int i = 0; i < matches.size(); i++) {
            System.out.println(String.format(i + 1 + ". " + "[%s][%s] %s",
                    matches.get(i).getTaskSymbol(),
                    matches.get(i).getStatus() ? "X" : " ",
                    matches.get(i).getDescription()));
        }

    }


}
