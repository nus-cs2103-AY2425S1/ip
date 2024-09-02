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
    public String addMessage(Task task) {
        return "added: " + task;
    }

    /**
     * Displays a message confirming the deletion of a task and the current number of tasks.
     *
     * @param task The task that was deleted.
     * @param tasks The current list of tasks.
     */
    public String deleteMessage(Task task, TaskList tasks) {
        return "deleted: " + task + ".\nYou now have " + (tasks.getNumItems() - 1) + " tasks in the list";
    }

    /**
     * Displays a message confirming that a task has been marked as completed.
     *
     * @param task The task that was marked as completed.
     */
    public String markMessage(Task task) {
        return "Nice lah.. Great job on doing work! I've marked it: " + task;
    }

    /**
     * Displays a message confirming that a task has been unmarked as incomplete.
     *
     * @param task The task that was unmarked as incomplete.
     */
    public String unmarkMessage(Task task) {
        return "ok... I've unmarked:" + task;
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
    public String showWelcome() {
        return "Hello, I am Monique,\nI am your personal assistant :)";
    }

    /**
     * Displays a goodbye message when the Monique application exits.
     */
    public String showGoodbye() {
        return "Goodbye! Have a great day! This window will close in 5 seconds";
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
    public String emptyListMessage() {
        return "There are no tasks in your List :)";
    }

    /**
     * Displays the search results for tasks that match a given search query.
     * If the provided list of tasks is empty, a message indicating no matches is printed.
     * Otherwise, it prints each matching task in the list, numbered sequentially.
     *
     * @param resultList the list of tasks that match the search query
     */
    public String showFindResults(ArrayList<Task> resultList) {
        StringBuilder sb = new StringBuilder();
        if (resultList.isEmpty()) {
            return "There are no tasks matching your search";
        } else {
            sb.append("Here are the matching tasks on your list:\n");
            for (int i = 0; i < resultList.size(); i++) {
                sb.append(String.valueOf(i + 1)).append(". ").append(resultList.get(i)).append("\n");
            }
        }
        return sb.toString();
    }

    public String printGuide() {
        return GuideText.GUIDE;
    }
}
