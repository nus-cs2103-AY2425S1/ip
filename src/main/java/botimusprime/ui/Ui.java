package botimusprime.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    private static final String greetingMessage =
            "____________________________________________________________\n" +
                    " Greetings, human. I am BotimusPrime.\n" +
                    " What can I do for you?\n" +
                    "____________________________________________________________";
    private static final String byeMessage =
            "____________________________________________________________\n" +
                    "Autobots, ROLL OUT!!!\n" +
                    "____________________________________________________________\n";
    private static final String line =
            "____________________________________________________________\n";

    public void greet() {
        System.out.println(greetingMessage);
    }

    public void bye() {
        System.out.println(byeMessage);
    }

    public void showLine() {
        System.out.println(line);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void showError() {
        System.out.println("Error, please try again");
    }
}