package chatBot.bot;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public static final String HLINE = "____________________________________________________________\n";

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    void showWelcome() {
        System.out.print(HLINE +
                " Hello! I'm 'B word'\n" +
                " What can I do for you?\n" +
                HLINE);
    }

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
