package atlas.ui;

import java.util.Scanner;

/**
 * Represents a ui class containing the methods to display various ui designs on the chatbot.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________";
    public final Scanner scanner = new Scanner(System.in);

    /**
     * Reads the command a user types into the chatbot.
     *
     * @return String The command the user has typed in next.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Greets the user when they start running the chatbot.
     */
    public void greet() {
        // @@author patorjk.com
        // Reused from https://patorjk.com/software/taag/#p=display&f=Isometric1&t=Ui
        // with minor modifications
        String logo = """
                     ___           ___           ___       ___           ___
                    /\\  \\         /\\  \\         /\\__\\     /\\  \\         /\\  \\
                   /::\\  \\        \\:\\  \\       /:/  /    /::\\  \\       /::\\  \\
                  /:/\\:\\  \\        \\:\\  \\     /:/  /    /:/\\:\\  \\     /:/\\ \\  \\
                 /::\\~\\:\\  \\       /::\\  \\   /:/  /    /::\\~\\:\\  \\   _\\:\\~\\ \\  \\
                /:/\\:\\ \\:\\__\\     /:/\\:\\__\\ /:/__/    /:/\\:\\ \\:\\__\\ /\\ \\:\\ \\ \\__\\
                \\/__\\:\\/:/  /    /:/  \\/__/ \\:\\  \\    \\/__\\:\\/:/  / \\:\\ \\:\\ \\/__/
                     \\::/  /    /:/  /       \\:\\  \\        \\::/  /   \\:\\ \\:\\__\\
                     /:/  /     \\/__/         \\:\\  \\       /:/  /     \\:\\/:/  /
                    /:/  /                     \\:\\__\\     /:/  /       \\::/  /
                    \\/__/                       \\/__/     \\/__/         \\/__/
                """;

        System.out.println("Hello from\n" + logo + '\n' + Ui.LINE);
        System.out.println("Hello! I'm Atlas\n" + "What can I do for you?");
        this.showLine();
    }

    /**
     * Prints a message with the divider.
     *
     * @param message String The message to be printed out.
     */
    public void print(String message) {
        System.out.println(Ui.LINE + '\n' + message);
    }

    /**
     * Prints an error message with the divider.
     *
     * @param message String The error message to be printed out.
     */
    public void showError(String message) {
        this.print(message);
    }

    /**
     * Prints an exit message when the user exits the chatbot.
     */
    public void exit() {
        this.print("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a divider.
     */
    public void showLine() {
        System.out.println(Ui.LINE + '\n');
    }
}
