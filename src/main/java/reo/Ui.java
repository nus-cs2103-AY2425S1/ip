package reo;

import reo.*;

public class Ui {
    /** The current user tasks. */
    TaskList tasks;
    public Ui (TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Displays the welcome message.
     */
    public void welcome() {
        System.out.println("----------------------\n" +
                "Hello! I'm reo.Reo.\nWhat can I do for you?\n"
                + "----------------------");
    }

    /**
     * Displays the current tasks, in a formatted manner.
     */
    public void list() {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += tasks.toString();
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the confirmation message after adding a reo.Todo object.
     *
     * @param t the reo.Todo object that was added
     */
    public void addTodo(Todo t) {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += "I've added this todo:\n " + t.toString() + "\n";
        toPrint += "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the error message from attempting to add a reo.Todo object.
     */
    public void addTodoError() {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += "Please enter a valid task name.\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the confirmation message after marking a task as completed.
     */
    public void mark(Task t) {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += "Good job! I've marked this item as done:\n";
        toPrint += t.toString() + "\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the error message from attempting to mark a reo.Task as complete.
     */
    public void markError() {
        String toPrint;
        toPrint = "----------------------\nPlease enter a valid task number.\n" +
                "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the confirmation message after marking a task as incomplete.
     */
    public void unmark(Task t) {
        String toPrint;
        toPrint = "----------------------\n";
        toPrint += "Get better, I've marked this item as not done:\n";
        toPrint += t.toString() + "\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the error message from attempting to mark a reo.Task as incomplete.
     */
    public void unmarkError() {
        String toPrint;
        toPrint = "----------------------\nPlease enter a valid task number.\n" +
                "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the confirmation message after adding a reo.Deadline object.
     *
     * @param d The deadline object that was added.
     */
    public void addDeadline(Deadline d) {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += "I've added this deadline:\n";
        toPrint += d.toString() + "\n";
        toPrint += "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the error message from attempting to add a reo.Deadline object.
     */
    public void addDeadlineError() {
        String toPrint;
        toPrint = "----------------------\nPlease enter a valid task name and deadline.\n" +
                "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the confirmation message after adding an reo.Event object.
     *
     * @param e The deadline object that was added.
     */
    public void addEvent(Event e) {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += "I've added this event:\n";
        toPrint += e.toString() + "\n";
        toPrint += "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the error message from attempting to add an reo.Event object.
     */
    public void addEventError() {
        String toPrint;
        toPrint = "----------------------\nPlease enter a valid task name and " +
                "to & from dates.\n----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the confirmation message after deleting a task.
     *
     * @param t The reo.Task object that was deleted.
     */
    public void delete(Task t) {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += "I've deleted this task:\n";
        toPrint += t.toString() + "\n";
        toPrint += "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the error message from attempting to delete a reo.Task.
     */
    public void deleteError() {
        String toPrint;
        toPrint = "----------------------\nPlease enter a valid task number.\n" +
                "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the error message from entering an invalid command.
     */
    public void enterCommandError() {
        String toPrint;
        toPrint = "----------------------\nERROR: Please enter a valid command.\n" +
                "----------------------";
        System.out.println(toPrint);
    }

    /**
     * Displays the goodbye message.
     */
    public void exit() {
        System.out.println("Bye!");
    }
}
