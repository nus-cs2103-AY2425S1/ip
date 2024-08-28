package demurebot.ui;

import demurebot.task.Task;
import demurebot.task.TaskList;

/**
 * The Ui class handles all user interface interactions for the DemureBot application.
 * It provides methods to display various messages and task lists to the user.
 */
public class Ui {
    /**
     * Displays the end message when the user exits the application.
     */
    public void displayEnd() {
        System.out.println("""
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
        
                """
        );
    }

    /**
     * Displays the start message when the user starts the application.
     */
    public void displayStart() {
        System.out.println("""
                ____________________________________________________________
                 Hello! I'm DemureBot
                 What can I do for you?
                ____________________________________________________________
    
                """
        );
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void displayMarkTask(Task task) {
        System.out.println("____________________________________________________________\n"
                + " Nice! I've marked this task as done:\n   "
                + task + "\n"
                + "____________________________________________________________\n\n"
        );
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void displayUnmarkTask(Task task) {
        System.out.println("____________________________________________________________\n"
                + " OK, I've marked this task as not done yet:\n   "
                + task + "\n"
                + "____________________________________________________________\n\n"
        );
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that has been deleted.
     * @param size The current number of tasks in the list.
     */
    public void displayDeleteTask(Task task, int size) {
        System.out.println("____________________________________________________________\n"
                + " Noted. I've removed this task:\n   "
                + task + "\n"
                + " Now you have " + size + " tasks in the list.\n"
                + "____________________________________________________________\n\n"
        );
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The task that has been added.
     * @param size The current number of tasks in the list.
     */
    public void displayAddTask(Task task, int size) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n  "
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n"
                + "____________________________________________________________\n\n"
        );
    }

    /**
     * Displays a message indicating that the task list is empty.
     */
    public void displayEmptyList() {
        System.out.println("""
            ____________________________________________________________
            There are no tasks in the list.
            ____________________________________________________________

            """
        );
    }

    /**
     * Displays the list of tasks.
     *
     * @param list The TaskList containing the tasks to be displayed.
     */
    public void displayList(TaskList list) {
        for (int i = 0; i < list.getSize(); i++) {
            Task task = list.getTask(i);
            System.out.println((i + 1) + "." + task);
        }
    }
}
