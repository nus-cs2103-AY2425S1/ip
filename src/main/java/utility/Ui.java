package utility;

import tasktypes.Task;

import java.util.ArrayList;

/**
 * The {@code Ui} class handles the user interface messages, displaying
 * various responses to the user based on their interactions with the application.
 */
public class Ui {
    
    /**
     * Displays the welcome message to the user when the application starts.
     */
    public String welcomeMessage() {
        String initialResponse = "____________________________________________________________\n"
                + "Hello! I'm Alpha\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        return initialResponse;
    }
    
    /**
     * Displays the list of tasks currently stored in the {@code TaskList}.
     *
     * @param taskList the {@code TaskList} containing the tasks to be displayed
     */
    public String listTask(TaskList taskList) {
        String echoResponse = "____________________________________________________________\n"
                + "Here are the tasks in your list:\n"
                + taskList.listWord() + "\n"
                + "____________________________________________________________\n";
        return echoResponse;
    }
    
    /**
     * Displays an error message indicating that no tasks were loaded from memory.
     */
    public String showLoadingError() {
        return "No Tasks Loaded from Memory";
    }
    
    /**
     * Displays a farewell message to the user when they exit the application.
     */
    public String byeMessage() {
        String echoResponse = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!" + "\n"
                + "____________________________________________________________\n";
        return echoResponse;
    }
    
    /**
     * Displays a message confirming that a new task has been added to the {@code TaskList}.
     *
     * @param taskList the {@code TaskList} containing the newly added task
     */
    public String addTaskMessage(TaskList taskList) {
        System.out.println(taskList.getTaskLists());
        String echoResponse = "____________________________________________________________ \n"
                + "Got it. I've added this task: \n"
                + taskList.lastTask().toString()
                + taskList.getLength() + "\n"
                + "____________________________________________________________ \n";
        return echoResponse;
    }
    
    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param modifiedRecord a string representing the task that was marked as not done
     */
    public String undoneMessage(String modifiedRecord) {
        String echoResponse = "____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n "
                + modifiedRecord + "\n"
                + "____________________________________________________________\n";
        return echoResponse;
    }
    
    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param modifiedRecord a string representing the task that was marked as done
     */
    public String doneMessage(String modifiedRecord) {
        String echoResponse = "____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + modifiedRecord + "\n"
                + "____________________________________________________________\n";
        return echoResponse;
    }
    
    /**
     * Formats and returns a string representing the tasks in the provided list.
     * Each task is numbered sequentially.
     *
     * @param taskList the list of tasks to be formatted
     * @return a formatted string of the tasks in the list
     */
    public String listSearchResult(ArrayList<Task> taskList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(i + 1).append(". ")
                .append(taskList.get(i).toString()).append("\n");
        }
        return result.toString();
    }
    
    /**
     * Prints the search results of tasks that match a specific search criterion.
     * The search results are displayed in a formatted manner with a header and footer.
     *
     * @param taskList the list of tasks that match the search criteria
     */
    public String searchTask(ArrayList<Task> taskList) {
        String echoResponse = "____________________________________________________________\n"
            + "Here are the matching tasks in your list:\n"
            + listSearchResult(taskList) + "\n"
            + "____________________________________________________________\n";
        return echoResponse;
    }
}
