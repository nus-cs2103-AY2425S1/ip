package boss;

import boss.tasks.Task;

/**
 * Represents the class that deals with the
 * interactions with the user.
 */
public class Ui {

    /**
     * Prints an error message when
     * an exception occurs!
     */
    public void showLoadingError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Returns user messages to print onto screen for GUI.
     *
     * @return String containing messages for user.
     */
    public String printAddTask(Task task, int size) {
        String toPrint = "";
        toPrint = toPrint + "Got it! I've added this task now!" + "\n";
        toPrint = toPrint + task + "\n";
        toPrint = toPrint + "Now you have " + size + " tasks in the list." + "\n";
        return toPrint;
    }


    /**
     * Returns the Chatbot's response when user marks a task.
     *
     * @param task string representation of the task user wants to mark
     * @return String containing chatbot's response
     */
    public String printMark(String task) {
        String toPrint;
        toPrint = "Nice! I have marked this task as done!" + "\n";
        toPrint = toPrint + task + "\n";
        return toPrint;
    }

    /**
     * Returns the Chatbot's response when user unmarks a task.
     *
     * @param task string representation of the task user wants to unmark
     * @return String containing chatbot's response
     */
    public String printUnmark(String task) {
        String toPrint;
        toPrint = "Nice! I have marked this task as undone!" + "\n";
        toPrint = toPrint + task + "\n";
        return toPrint;
    }

    /**
     * Returns the Chatbot's response when user deletes a task.
     *
     * @param task string representation of the task user wants to delete
     * @return String containing chatbot's response
     */
    public String printDelete(String task) {
        String toPrint;
        toPrint = "Ok! This task has been removed!" + "\n";
        toPrint = toPrint + task + "\n";
        return toPrint;
    }

}
