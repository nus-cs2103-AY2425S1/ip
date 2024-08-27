package henry;

import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {

    /**
     * Prints greetings
     *
     */
    public void greetings() {
        String greetings = "Hello! I'm Henry\n"
                + "What can I do for you?\n";
        System.out.println(greetings);
    }

    /**
     * Prints exit
     *
     */
    public void bye() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }

    /**
     * Returns user input
     *
     * @return user input
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints error message
     */
    public void showError(String message) {
        System.out.println(message + "\n");
    }
}
