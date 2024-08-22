import java.util.Scanner;

/**
 * Echoa is a class that demonstrates a simple console-based interaction.
 */

public class Echoa {
    /**
     * The main method is the entry point to the application.
     * It greets the user with a series of messages and allows input.
     * The user is able to key tasks which will be added to the tasklist,
     * view their list of tasks using "list"
     * and end the chat using "bye".
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasklist = new String[100];
        int taskcount = 0;

        System.out.println("Hello! I'm Echoa.");
        System.out.println("What can I do for you?\n");

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < taskcount; i++) {
                    int index = i + 1;
                    System.out.println(index + ". "  +  tasklist[i]);
                }
                System.out.println();
            } else {
                System.out.println("added: " + command + "\n");
                tasklist[taskcount] = command;
                taskcount++;
            }
        }
    }
}
