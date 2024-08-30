package atreides.ui;

import java.util.Scanner;

public class Ui {
    private final String LOGO = "          _            _     _\n"
                             +  "    / \\  | |          (_)   | |\n"
                             +  "   /   \\ | |_ _ __ ___ _  __| | ___  ___\n"
                             +  "  / / \\ \\| __| '__/ _ \\ |/ _` |/ _ \\/ __|\n"
                             +  " / _____ \\ |_| | |  __/ | (_| |  __/\\__ \\\n"
                             +  "/_/     \\_\\__|_|  \\___|_|\\__,_|\\___||___/\n";
    private final String INTRO = "Glory to house\n" + LOGO;
    private final String OUTRO = "Praise be Muad Dib\n" + "GoodBye\n";;
    private final String ERROR_STRING = "File cannot be found\n";


    public void showMessage(String message) {
        System.out.println(new Response(message));
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

    public void showLoadingError() {
        showError(ERROR_STRING);
    }

    public void showExit() {
        showMessage(OUTRO);
    }
}
