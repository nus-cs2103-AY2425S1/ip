/**
 * Produces greetings for users and initializes chat.
 */

import java.util.Scanner;

public class Nether {
    public static void main(String[] args) {
        String logo = " _   _      _   _        \n"
                + "| \\ | | ___| |_| |__  ___ _ __ \n"
                + "|  \\| |/ _ \\ __| '_ \\/ _ \\ '__|\n"
                + "| |\\  |  __/ |_| | | ||__/ |  \n"
                + "|_| \\_|\\___|\\__|_| |_\\___|_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello sir! I'm Nether");
        System.out.println("What can I do for you today?");
        System.out.println("____________________________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. If you have any more queries in the future, feel free to ask. Enjoy your day!");
        System.out.println("____________________________________________________________");
    }
}
