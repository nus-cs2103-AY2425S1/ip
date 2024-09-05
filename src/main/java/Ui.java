/**
 * Represents the user interface class for displaying information
 * @author He Yiheng
 */
public class Ui {
    private final String INTRO_MSG = "____________________________________________________________\n"
            + " Hello! I'm Lemon\n"
            + " What can I do for you?\n";
    private final String END_MSG = " Bye. Hope to see you again soon!\n"
            + "____________________________________________________________\n";
    private final String BAR = "____________________________________________________________";
    private final String EMPTY_LIST_MSG = " Sowwy, theres currently no tasks in your list.\n " +
            " Ill be happy to add some for you OwO!";
    private final String LIST_MSG = " Here are the tasks in your list:";
    private final String MARK_MSG = " Nice! I've marked this task as done:";
    private final String UNMARK_MSG = " OK, I've marked this task as not done yet:";
    private final String ADD_TASK_MSG = " Got it. I've added this task:";
    private final String DELETE_TASK_MSG = " Noted. I've removed this task:";

    /**
     * Prints the intro message
     */
    public void printIntroMsg() {
        System.out.println(INTRO_MSG);
    }

    /**
     * Prints the end message
     */
    public void printEndingMsg() {
        System.out.println(END_MSG);
    }

    /**
     * Prints the bar message
     */
    public void printBarMsg() {
        System.out.println(BAR);
    }

    /**
     * Prints the message when the list is empty
     */
    public void printEmptyListMsg() {
        System.out.println(EMPTY_LIST_MSG);
    }

    /**
     * Prints the message for lists
     */
    public void printListMsg(String listStr) {
        System.out.println(LIST_MSG);
        System.out.println(listStr);
    }

    /**
     * Prints the message when marking a task
     */
    public void printMarkMsg() {
        System.out.println(MARK_MSG);
    }

    /**
     * Prints the message when unmarking a task
     */
    public void printUnmarkMsg() {
        System.out.println(UNMARK_MSG);
    }

    /**
     * Prints the message after adding a task
     */
    public void printAddTaskMsg(String addedTask, int numTasks) {
        System.out.println(ADD_TASK_MSG);
        System.out.println("   " + addedTask);
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Prints the message after deleting a task
     */
    public void printDeleteTaskMsg(String deletedTask, int numTasks) {
        System.out.println(DELETE_TASK_MSG);
        System.out.println("   " + deletedTask);
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
    }

    public void printException(Exception e) {
        System.out.println(e.toString());
    }

    public void printException(String errorMsg) {
        System.out.println(errorMsg);
    }
}
