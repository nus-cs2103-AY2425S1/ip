import java.util.Scanner;

/**
 * Echoa is a class that demonstrates a simple console-based interaction.
 */

public class Echoa {
    /**
     * The main method is the entry point to the application.
     * It greets the user with a series of messages
     * and allows input which would be echoed.
     * The program will be terminated when the user keys in "bye".
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Echoa.");
        System.out.println("What can I do for you?\n");

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else {
                System.out.println(command + "\n");
            }
        }
    }
}
