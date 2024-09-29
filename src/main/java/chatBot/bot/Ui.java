package chatbot.bot;

import java.util.Scanner;

/** Ui deals with all the interactions with the user */
public class Ui {
    public static final String HLINE = "____________________________________________________________\n";
    private Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    void showWelcome() {
        System.out.print(HLINE
                + " Hello! I'm 'B word'\n"
                + " What can I do for you?\n"
                + HLINE);
    }

    /** Prints the exiting message */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void showLine() {
        System.out.println(HLINE);
    }

    String readCommand() {
        return this.scanner.nextLine();
    }

    void showError(String error) {
        System.out.println(error);
    }

    void showLoadingError() {
        System.out.println("Something went wrong when loading file");
    }

    static void printAnything(String s) {
        System.out.println(s);
    }
}
