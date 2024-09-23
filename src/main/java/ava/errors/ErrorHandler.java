package ava.errors;

/**
 * Handles errors.
 */
public class ErrorHandler {

    /**
     * Handles the received error.
     *
     * @param errors Errors received.
     */
    public static void handle(Error... errors) {

    }

    /**
     * Handles the received error.
     *
     * @param error Error received.
     * @param displayError Error message to display.
     */
    public static void handle(Error error, String displayError) {
        System.out.println(error + ": " + displayError);
    }

    /**
     * Handles the received input error.
     *
     * @param error Input error received.
     * @param displayError Error message to display.
     */
    public static void handle(InputError error, String displayError) {
        System.out.println(error + ": " + displayError);
    }


}
