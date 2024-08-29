package botimusprime.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    private static final String GREETING_MESSAGE =
            "____________________________________________________________\n"
                    + " Greetings, human. I am BotimusPrime.\n"
                            + " What can I do for you?\n"
                                    + "____________________________________________________________";
    private static final String BYE_MESSAGE =
            "____________________________________________________________\n"
                    + "Autobots, ROLL OUT!!!\n"
                            + "____________________________________________________________\n";
    private static final String LINE =
            "____________________________________________________________\n";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }



    public void greet() {
        System.out.println(GREETING_MESSAGE);
    }

    public void bye() {
        System.out.println(BYE_MESSAGE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine().
                trim();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void showError() {
        System.out.println("Error, please try again");
    }
}