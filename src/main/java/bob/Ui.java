package bob;

import java.util.Scanner;

/**
 * Represents Bob's user interface.
 */
public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads in input from the user.
     *
     * @return input from user.
     */
    public String readInput() {
        this.showBar();
        System.out.print("> ");
        String input = scanner.nextLine();
        this.showBar();
        return input;
    }

    /**
     * Displays whatever text is passed into the method.
     *
     * @param text text to be displayed.
     */
    public void show(String text) {
        System.out.println(text);
    }

    /**
     * Displays the help message in a new line.
     */
    public void advise() {
        System.out.println(Ui.HELP_MESSAGE);
    }

    /**
     * Displays a bar, typically used to signal the start and end of Bob's response.
     */
    public void showBar() {
        System.out.println(Ui.BAR);
    }

    private static final String BAR = "____________________________________________________________";

    private static final String HELP_MESSAGE = "Key in \"I need help.\" for additional help.";
}
