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
    public String printFormattedResponse(String response) {
//        for (int i = 0; i < 60; i++) {
//            System.out.print("_");
//        }
//        System.out.print("\n");
//        System.out.println(response);
//        for (int i = 0; i < 60; i++) {
//            System.out.print("_");
//        }
//        System.out.println("\n");
        return response;
    }

    /**
     * Prints a formatted goodbye message.
     * This method uses the printFormattedResponse method to display the message.
     */
    public String printByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String printFindMessage(String s) {
        return String.format("Here are the matching tasks in your list:\n%s", s);
    }

    public String printTaskListMessage(String s) {
        return String.format("Here are the task in your list:\n%s",s);
    }

    /**
     * Prints a formatted error message.
     *
     * @param s The specific error message to be appended to the standard error prefix.
     */
    public String printErrorMessage(String s) {
        return String.format("Oops! An error has occurred " + s);
    }

    public String printNoInputMatchedMessage() {
        return String.format("No matched input, The available inputs are\n deadline\n event\n todo\n mark\n unmark\n list\n delete\n bye");
    }
}
