package monique.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import monique.GuideText;
import monique.task.Task;
import monique.tasklist.TaskList;


/**
 * The <code>Ui</code> class handles all user interactions for the Monique application.
 * It is responsible for displaying messages, reading user input, and providing feedback
 * on the actions performed on tasks.
 */
public class Ui {

    public static final String HORIZONTAL_LINE = "_____________________________________________\n";
    private final BufferedReader br;

    /**
     * Constructs a new <code>Ui</code> object.
     * Initializes the <code>BufferedReader</code> for reading user input from the console.
     */
    public Ui() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Displays a message confirming the addition of a task.
     *
     * @param task The task that was added.
     */
    public void addMessage(Task task) {
        System.out.println("added: " + task);
    }

    /**
     * Displays a message confirming the deletion of a task and the current number of tasks.
     *
     * @param task The task that was deleted.
     * @param tasks The current list of tasks.
     */
    public void deleteMessage(Task task, TaskList tasks) {
        System.out.println("deleted: " + task);
        System.out.println("You now have " + tasks.getNumItems() + " tasks in the list");
    }

    /**
     * Displays a message confirming that a task has been marked as completed.
     *
     * @param task The task that was marked as completed.
     */
    public void markMessage(Task task) {
        System.out.println("Nice lah.. Great job on doing work! I've marked it: " + task);
    }

    /**
     * Displays a message confirming that a task has been unmarked as incomplete.
     *
     * @param task The task that was unmarked as incomplete.
     */
    public void unmarkMessage(Task task) {
        System.out.println("ok... I've unmarked:" + task);
    }

    /**
     * Displays a horizontal line to separate different sections of output.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a welcome message when the Monique application starts.
     */
    public void showWelcome() {
        System.out.println("Hello, I am Monique,\nI am your personal assistant :)\n ");
    }

    /**
     * Displays a goodbye message when the Monique application exits.
     */
    public void showGoodbye() {
        System.out.println("Monique: Goodbye! Have a great day!");
    }

    /**
     * Reads a command input by the user from the console.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        String line = "";
        try {
            line = this.br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    /**
     * Displays a message indicating that the task list is empty.
     */
    public void emptyListMessage() {
        System.out.println("There are no tasks in your List :)");
    }

    /**
     * Displays the search results for tasks that match a given search query.
     * If the provided list of tasks is empty, a message indicating no matches is printed.
     * Otherwise, it prints each matching task in the list, numbered sequentially.
     *
     * @param resultList the list of tasks that match the search query
     */
    public void showFindResults(ArrayList<Task> resultList) {
        if (resultList.isEmpty()) {
            System.out.println("There are no tasks matching your search");
        } else {
            System.out.println("Here are the matching tasks on your list:");
            for (int i = 0; i < resultList.size(); i++) {
                System.out.println(String.valueOf(i + 1) + ". " + resultList.get(i));
            }
        }
    }
    public void printGuide() {
        System.out.println(GuideText.GUIDE);
    }
}
