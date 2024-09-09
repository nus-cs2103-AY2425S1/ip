package monique.ui;

import java.io.BufferedReader;
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
    public static final int INDEX_OFFSET = 1;
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
     * @return A confirmation message including the added task.
     */
    public String addMessage(Task task) {
        return "added: " + task;
    }

    /**
     * Displays a message confirming the deletion of a task and the current number of tasks.
     *
     * @param task The task that was deleted.
     * @param tasks The current list of tasks.
     * @return A confirmation message including the deleted task and the updated task count.
     */
    public String deleteMessage(Task task, TaskList tasks) {
        return "deleted: " + task + ".\nYou now have " + (tasks.getNumItems() - INDEX_OFFSET) + " tasks in the list";
    }

    /**
     * Displays a message confirming that a task has been marked as completed.
     *
     * @param task The task that was marked as completed.
     * @return A confirmation message including the marked task.
     */
    public String markMessage(Task task) {
        return "Nice lah.. Great job on doing work! I've marked it: " + task;
    }

    /**
     * Displays a message confirming that a task has been unmarked as incomplete.
     *
     * @param task The task that was unmarked as incomplete.
     * @return A confirmation message including the unmarked task.
     */
    public String unmarkMessage(Task task) {
        return "ok... I've unmarked:" + task;
    }


    /**
     * Displays a welcome message when the Monique application starts.
     *
     * @return A welcome message.
     */
    public String showWelcome() {
        return "Hello, I am Monique,\nI am your personal assistant :)";
    }

    /**
     * Displays a goodbye message when the Monique application exits.
     *
     * @return A goodbye message.
     */
    public String showGoodbye() {
        return "Goodbye! Have a great day! This window will close in 3 seconds";
    }

    /**
     * Displays a message indicating that the task list is empty.
     *
     * @return A message indicating that the task list is empty.
     */
    public String emptyListMessage() {
        return "There are no tasks in your List :)";
    }

    /**
     * Displays the search results for tasks that match a given search query.
     * If the provided list of tasks is empty, a message indicating no matches is returned.
     * Otherwise, it returns a string with each matching task in the list, numbered sequentially.
     *
     * @param resultList the list of tasks that match the search query
     * @return A message listing the matching tasks or indicating no matches.
     */
    public String showFindResults(ArrayList<Task> resultList) {
        StringBuilder sb = new StringBuilder();
        if (resultList.isEmpty()) {
            return "There are no tasks matching your search";
        } else {
            sb.append("Here are the matching tasks on your list:\n");
            for (int i = 0; i < resultList.size(); i++) {
                sb.append(String.valueOf(i + INDEX_OFFSET)).append(". ").append(resultList.get(i)).append("\n");
            }
        }
        return sb.toString();
    }
    /**
     * Retrieves the guide text for the application.
     *
     * @return The guide text from <code>GuideText.GUIDE</code>.
     */
    public String printGuide() {
        return GuideText.GUIDE;
    }
}
