package fridayproject;

/*
 * Represents the user interface of the program.
 * Ui deals with interactions with the user.
 */
public class Ui {

    /*
    * Displays the welcome message when the program starts.
    */
    public String displayWelcome() {
        String greeting = " Hello! I'm Friday\n Great to see you!\n What can I do for you?\n";
        return greeting;
    }

    /*
    * Displays the farewell message when the program ends.
    */
    public String displayFarewell() {
        String farewell = "Bye. Hope to see you again soon!\n";
        return farewell;
    }

    /*
     * Displays loading error message.
     */
    public String displayLoadingError() {
        return "Error loading tasks from file: ";
    }

    /*
     * Displays an unknown command error message.
     */
    public String displayUnknownCommandError() {
        return "I'm sorry, but I don't know what that means :(((\n" 
        + "Please enter a valid task description.";
    }

    /*
     * Displays a custom error message.
     * @param message The custom error message to be displayed.
     */
    public String displayError(String message) {
        return "Error: " + message;
    }
}
