package hoodini;

/**
 * Class to handle interations with user
 */
public class Ui {
    /**
     * Default Constructor to create new UI object.
     */
    public Ui() {

    }


    /**
     * Handles the goodbye message,
     * takes in no parameters,
     * prints the parting statement.
     */
    public String showGoodbye() {
        return "Bye! "
                + "Come back to Hoodini soon!";
    }

    /**
     * Handles the no file found message,
     * takes in no parameters,
     * prints the no file statement.
     */
    public String noFileFound() {
        return "File not found, "
                + "I will create a new file for you!";
    }

    /**
     * Handles storing message,
     * print message when user
     * stores a task
     * @param input Takes in input object
     *              to output the relevant string
     * @param counter Takes in a counter number to determine
     *                number of items in list
     */
    public String store(Input input, int counter) {
        return "Noted. " + "I have added this task:\n"
                + input + "\nYou have " + counter + " tasks in the list.";
    }

    /**
     * Handles delete message
     * prints a message when user
     * deletes a task.
     * @param input Takes in input object
     *              to output the relevant string
     *              of deleted task.
     * @param counter Takes in a counter number to determine
     *                number of items in list.
     *
     */
    public String delete(Input input, int counter) {
        return "Noted. " + "I have deleted this task:"
                + input + "\nYou have " + counter
                + " tasks in the list.";
    }

    /**
     * Handles invalid input message
     */
    public String invalidInput() {
        return "Invalid number, "
                + "enter a valid number";
    }

    /**
     * Handles invalid task message
     */
    public String invalidTask() {
        return "Invalid task, "
                + "please enter a valid task";
    }

    /**
     * Handles invalid date message
     * @return String message
     */
    public String invalidDate() {
        return "Please enter the date "
                + "in the format yyyy-mm-dd";
    }

}
