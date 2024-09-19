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
        String initialResponse = "Hello! I'm Alpha\n"
                + "What can I do for you?\n";
        return initialResponse;
    }
    
    /**
     * Displays the list of tasks currently stored in the {@code TaskList}.
     *
     * @param taskList the {@code TaskList} containing the tasks to be displayed
     */
    public String listTask(TaskList taskList) {
        String echoResponse = "Here are the tasks in your list:\n"
                + taskList.listWord() + "\n";
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
        String echoResponse = "Bye. Hope to see you again soon!" + "\n";
        return echoResponse;
    }
    
    /**
     * Displays a message confirming that a new task has been added to the {@code TaskList}.
     *
     * @param taskList the {@code TaskList} containing the newly added task
     */
    public String addTaskMessage(TaskList taskList) {
        System.out.println(taskList.getTaskLists());
        String echoResponse = "Got it. I've added this task: \n"
                + taskList.lastTask().toString()
                + taskList.getLength() + "\n";
        return echoResponse;
    }
    
    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param modifiedRecord a string representing the task that was marked as not done
     */
    public String undoneMessage(String modifiedRecord) {
        String echoResponse = "OK, I've marked this task as not done yet:\n "
                + modifiedRecord + "\n";
        return echoResponse;
    }
    
    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param modifiedRecord a string representing the task that was marked as done
     */
    public String doneMessage(String modifiedRecord) {
        String echoResponse = "Nice! I've marked this task as done:\n"
                + modifiedRecord + "\n";
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
        String echoResponse = "Here are the matching tasks in your list:\n"
                + listSearchResult(taskList) + "\n";
        return echoResponse;
    }
    
    /**
     * Displays a message confirming that a task has been deleted from the {@code TaskList}.
     *
     * @param deleteTaskNotice a string representing the task that was deleted
     * @param numberOfTasks a string indicating the number of tasks remaining
     * @return a confirmation message with the deleted task and the remaining number of tasks
     */
    public String deleteTaskMessage(String deleteTaskNotice, String numberOfTasks) {
        String echoResponse = "";
        if (deleteTaskNotice.contains("Current Task List Length")) {
            echoResponse = deleteTaskNotice;
        } else {
            echoResponse = "Noted. I've removed this task:\n "
                    + deleteTaskNotice + "\n" + numberOfTasks;
        }
        return echoResponse;
    }
    
    /**
     * Displays a message confirming that the tasks have been sorted based on urgency.
     *
     * @return a message indicating successful sorting of tasks
     */
    public String sortTaskMessage() {
        String echoResponse = "Tasks sorted based on decreasing urgency!";
        return echoResponse;
    }
    
    /**
     * Displays a reminder message listing the tasks due this week.
     *
     * @param listTasks a string representing the tasks due this week
     * @return a message with the list of tasks due and a motivational note
     */
    public String reminderMessage(String listTasks) {
        String echoResponse = "These are the Tasks that are due this week: \n" +
                listTasks + "\n" + "Try your best! :)";
        return echoResponse;
    }
    
    /**
     * Displays a warning message showing the number of tasks.
     *
     * @param taskNumber is an int representing the number of tasks
     * @return a message with a warning and the total number of tasks
     */
    
    public String indexExceedMessage(int taskNumber) {
        return "Index specified exceeds number of tasks: " + String.valueOf(taskNumber);
    }
}
