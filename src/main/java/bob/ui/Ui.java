package bob.ui;

import bob.parser.Parser;

/**
 * Class that handles interactions with the user.
 */
public class Ui {
    private Parser parser;

    /**
     * Prints lines above and below a given String input.
     *
     * @param text String input.
     */
    public static void printLines(String text) {
        String textToPrint = "\t____________________________________________________________\n"
                + "\t"
                + text
                + "\n"
                + "\t____________________________________________________________\n";
        System.out.println(textToPrint);
    }


    public void showWelcome() {
        String welcome = "Hello! I'm Bob\n"
                + "\tWhat can I do for you?";
        Ui.printLines(welcome);
    }

    public void showLoadingError() {
        String loadingError = "Sorry! I'm unable to load the file\n";
        Ui.printLines(loadingError);
    }
}
