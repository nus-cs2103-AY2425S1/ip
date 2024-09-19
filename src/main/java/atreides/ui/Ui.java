package atreides.ui;

import java.util.Scanner;

/**
 * Represents the logic for the Ui that handles the printing to user and reading user commands
 */
public class Ui {
    private static final String LOGO = "          _            _     _\n"
                             + "    / \\  | |          (_)   | |\n"
                             + "   /   \\ | |_ _ __ ___ _  __| | ___  ___\n"
                             + "  / / \\ \\| __| '__/ _ \\ |/ _` |/ _ \\/ __|\n"
                             + " / _____ \\ |_| | |  __/ | (_| |  __/\\__ \\\n"
                             + "/_/     \\_\\__|_|  \\___|_|\\__,_|\\___||___/\n";
    private static final String INTRO = "Glory to house\n" + LOGO;
    private static final String OUTRO = "Praise be Muad Dib\n" + "GoodBye\n";
    private static final String ERROR_STRING = "File cannot be found\n";

    /**
     * Ui will print out message to the user with the correct indentation and headers
     * @param message
     */
    public void showMessage(String message) {
        System.out.println(new Response(message));
    }

    public String showStringWelcome() {
        return INTRO;
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showWelcome() {
        showMessage(INTRO);
    }

    public void showError(String error) {
        showMessage(error);
    }

    public String showStringLoadingError() {
        return ERROR_STRING;
    }

    public void showLoadingError() {
        showError(ERROR_STRING);
    }

    public void showExit() {
        showMessage(OUTRO);
    }

    public String showStringExit() {
        return new Response(OUTRO).toString();
    }
}
