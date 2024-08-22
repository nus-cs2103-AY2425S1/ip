import java.util.Scanner;

/**
 * Nayana is a class that demonstrates a simple console-based interaction.
 * It prints an ASCII logo and several lines of text to the console.
 */
public class Nayana {

    /**
     * The main method is the entry point of the application.
     * It prints a logo and a series of messages to the console.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String logo =
              "                                                            \n" +
                    " ___ .-.     .---.   ___  ___    .---.   ___ .-.     .---.  \n" +
                    "(   )   \\   / .-, \\ (   )(   )  / .-, \\ (   )   \\   / .-, \\ \n" +
                    " |  .-. .  (__) ; |  | |  | |  (__) ; |  |  .-. .  (__) ; | \n" +
                    " | |  | |    .'`  |  | |  | |    .'`  |  | |  | |    .'`  | \n" +
                    " | |  | |   / .'| |  | '  | |   / .'| |  | |  | |   / .'| | \n" +
                    " | |  | |  | /  | |  '  `-' |  | /  | |  | |  | |  | /  | | \n" +
                    " | |  | |  ; |  ; |   `.__. |  ; |  ; |  | |  | |  ; |  ; | \n" +
                    " | |  | |  ' `-'  |   ___ | |  ' `-'  |  | |  | |  ' `-'  | \n" +
                    "(___)(___) `.__.'_.  (   )' |  `.__.'_. (___)(___) `.__.'_. \n" +
                    "                      ; `-' '                               \n" +
                    "                       .__.'                                ";

        System.out.println("Hello from" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Nayana");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        System.out.println("Bye!!! Hope to help you again soon!");
        System.out.println("____________________________________________________________");
    }
}
