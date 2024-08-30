package duke;

/**
 * Handles user interactions and displays messages to the user.
 */
public class Ui {

    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Displays a farewell message to the user.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list of tasks to the user.
     *
     * @param taskList the list of tasks to be printed
     */
    public void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            int serial = i + 1;
            Task task = taskList.getTask(i);
            System.out.println(serial + "." + task.toString());
        }
    }

}
