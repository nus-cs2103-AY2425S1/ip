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
        Task[] tasklist = new Task[100];

        int taskcount = 0;

        System.out.println("Hello! I'm Echoa.");
        System.out.println("What can I do for you?\n");

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (command.equals("list")) {
                System.out.println("My Task List");
                if (taskcount == 0) {
                    System.out.println("No tasks yet :o");
                } else {
                    for (int i = 0; i < taskcount; i++) {
                        int index = i + 1;
                        System.out.println(index + ". " + tasklist[i].toString());
                    }
                }
                System.out.println();
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasklist[index].markAsDone();
                System.out.println("Task marked :)");
                System.out.println(tasklist[index].toString() + "\n");
            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasklist[index].unmarkAsUndone();
                System.out.println("Task unmarked :(");
                System.out.println(tasklist[index].toString() + "\n");
            } else {
                System.out.println("added: " + command + "\n");
                tasklist[taskcount] = new Task(command);
                tasklist[taskcount].unmarkAsUndone();
                taskcount++;
            }
        }
    }
}
