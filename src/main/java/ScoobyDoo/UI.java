package ScoobyDoo;

/**
 * The UI class handles the user interface output formatting for the ScoobyDoo application.
 */
public class UI {

    /**
     * Prints a formatted response with decorative underscores above and below the message.
     *
     * @param response The string message to be printed in a formatted manner.
     */
    public void printFormattedResponse(String response) {
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
        System.out.println(response);
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println("\n");
    }

    /**
     * Prints a formatted goodbye message.
     * This method uses the printFormattedResponse method to display the message.
     */
    public void printByeMessage() {
        printFormattedResponse("Bye. Hope to see you again soon!");
    }

    public void printFindMessage(String s) {
        printFormattedResponse(String.format("Here are the matching tasks in your list:\n%s", s));
    }

    public void printTaskListMessage(String s) {
        printFormattedResponse(String.format("Here are the task in your list:\n%s",s));
    }

    /**
     * Prints a formatted error message.
     *
     * @param s The specific error message to be appended to the standard error prefix.
     */
    public void printErrorMessage(String s) {
        printFormattedResponse("Oops! An error has occurred " + s);
    }
}
