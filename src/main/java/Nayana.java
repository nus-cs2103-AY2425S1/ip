import java.util.Scanner;

/**
 * Nayana is a class that demonstrates a simple console-based interaction.
 * It prints an ASCII logo and several lines of text to the console.
 */
public class Nayana {

    /**
     * The main method is the entry point of the application.
     * It prints a logo and a series of messages to the console.
     * and continues to process user input until the user types "bye"
     * Commands include listing tasks or adding new tasks.
     * The tasks can be marked as done or not done
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        TaskList taskList = new TaskList();
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye!!! Hope to help you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.equals("list")){
                System.out.println(taskList);
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                taskList.markAsDone(index);
            } else if (command.startsWith("unmark")){
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                taskList.markAsNotDone(index);
            } else {
                Task nextTask = new Task(command);
                taskList.addTask(nextTask);
            }
        }
        scanner.close();
    }
}
