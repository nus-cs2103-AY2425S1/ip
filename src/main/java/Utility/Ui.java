package Utility;

/**
 * The {@code Ui} class handles the user interface messages, displaying
 * various responses to the user based on their interactions with the application.
 */
public class Ui {
    
    /**
     * Displays the welcome message to the user when the application starts.
     */
    public void welcomeMessage() {
        String initialResponse = "____________________________________________________________\n"
                + "Hello! I'm Alpha\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(initialResponse);
    }
    
    /**
     * Displays the list of tasks currently stored in the {@code TaskList}.
     *
     * @param taskList the {@code TaskList} containing the tasks to be displayed
     */
    public void listTask(TaskList taskList) {
        String echoResponse = "____________________________________________________________\n"
                + "Here are the tasks in your list:\n"
                + taskList.listWord() + "\n"
                + "____________________________________________________________\n";
        System.out.println(echoResponse);
    }
    
    /**
     * Displays an error message indicating that no tasks were loaded from memory.
     */
    public void showLoadingError() {
        System.out.println("No Tasks Loaded from Memory");
    }
    
    /**
     * Displays a farewell message to the user when they exit the application.
     */
    public void byeMessage() {
        String echoResponse = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!" + "\n"
                + "____________________________________________________________\n";
        System.out.println(echoResponse);
    }
    
    /**
     * Displays a message confirming that a new task has been added to the {@code TaskList}.
     *
     * @param taskList the {@code TaskList} containing the newly added task
     */
    public void addTaskMessage(TaskList taskList) {
        String echoResponse = "____________________________________________________________ \n"
                + "Got it. I've added this task: \n"
                + taskList.lastTask().toString()
                + taskList.getLength() + "\n"
                + "____________________________________________________________ \n";
        System.out.println(echoResponse);
    }
    
    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param modifiedRecord a string representing the task that was marked as not done
     */
    public void undoneMessage(String modifiedRecord) {
        String echoResponse = "____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n "
                + modifiedRecord + "\n"
                + "____________________________________________________________\n";
        System.out.println(echoResponse);
    }
    
    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param modifiedRecord a string representing the task that was marked as done
     */
    
    public void doneMessage(String modifiedRecord) {
        String echoResponse = "____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + modifiedRecord + "\n"
                + "____________________________________________________________\n";
        System.out.println(echoResponse);
    }
}
