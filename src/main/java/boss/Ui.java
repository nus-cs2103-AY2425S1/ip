package boss;

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
     * Returns the Chatbot's response when user marks a task.
     *
     * @param task string representation of the task user wants to mark
     * @return String containing chatbot's response
     */
    public String printMark(String task) {
        String toPrint = "";
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
        String toPrint = "";
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
        String toPrint = "";
        toPrint = "Ok! This task has been removed!" + "\n";
        toPrint = toPrint + task + "\n";
        return toPrint;
    }

}
