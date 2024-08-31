package bob;

import java.util.Scanner;

public class Ui {
    private static final String[] welcome = {
            "Hello! I'm bob.Bob", "What can I do for you?"
    };
    private static final String[] farewell = {
            " Bye. Hope to see you again soon!"
    };

    public void showWelcome() {
        Printer.prettyPrint(Ui.welcome);
    }
    public void showFarewell() {
        Printer.prettyPrint(Ui.farewell);
    }
    public void showError(String msg) {
        System.out.println(msg);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
