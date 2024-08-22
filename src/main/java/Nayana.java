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

            } else if (command.startsWith("deadline")){
                String[] parts = command.split(" /by ");
                String description = parts[0].trim();
                String deadline = parts[1].trim();

                Deadlines nextTask = new Deadlines(description,deadline);
                taskList.addTask(nextTask);

            } else if (command.startsWith("event")) {
                String[] fromParts = command.split(" /from ");
                String description = fromParts[0].trim();

                String[] toParts = fromParts[1].split(" /to ");
                String startTime = toParts[0].trim();
                String endTime = toParts[1].trim();

                Event nextTask = new Event(description,startTime,endTime);
                taskList.addTask(nextTask);

            } else if (command.startsWith("todo")){
                String[] parts = command.split("todo ");
                String description = parts[1].trim();
                ToDos nextTask = new ToDos(description);
                taskList.addTask(nextTask);

            } else {
                System.out.println("____________________________________________________________");
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
