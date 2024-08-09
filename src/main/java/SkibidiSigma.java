import java.util.Scanner;

public class SkibidiSigma {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";

        Scanner scanner = new Scanner(System.in);

        System.out.println(horizontalLine + "\nHello! I'm SkibidiSigma!\n" + "\nWhat can I do for you?\n" + horizontalLine);

        while (true) {
            String userInput = scanner.nextLine().trim();

            if ("bye".equalsIgnoreCase(userInput)) {
                System.out.println(
                        horizontalLine +
                        "\nCatch ya on the flip side, my dude! \uD83D\uDE80\uD83E\uDD2F See ya soon!\n" +
                        horizontalLine);
                break;
            }

            if ("list".equalsIgnoreCase(userInput)) {
                System.out.println(horizontalLine);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println(horizontalLine);
            } else if (userInput.toLowerCase().startsWith("mark")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    System.out.println(horizontalLine);
                    tasks[taskNumber - 1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[taskNumber - 1]);
                    System.out.println(horizontalLine);
                } catch (Exception e) {
                    System.out.println("Invalid command syntax. Usage: mark <task_number>");
                    System.out.println(horizontalLine);
                }
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    System.out.println(horizontalLine);
                    tasks[taskNumber - 1].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[taskNumber - 1]);
                    System.out.println(horizontalLine);
                } catch (Exception e) {
                    System.out.println("Invalid command syntax. Usage: unmark <task_number>");
                    System.out.println(horizontalLine);
                }
            } else if (userInput.toLowerCase().startsWith("todo ")) {
                String description = userInput.substring(5);
                Task todo = new Todo(description);
                tasks[taskCount++] = todo;
                System.out.println(horizontalLine);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + taskCount + " tasks in the list.\n");
                System.out.println(horizontalLine);
            } else if (userInput.toLowerCase().startsWith("deadline ")) {
                String[] parts = userInput.split(" /by ");
                String description = parts[0].substring(9);
                String by = parts[1];
                Task deadline = new Deadline(description, by);
                tasks[taskCount++] = deadline;
                System.out.println(horizontalLine);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + taskCount + " tasks in the list.\n");
                System.out.println(horizontalLine);
            } else if (userInput.toLowerCase().startsWith("event ")) {
                String[] parts = userInput.split(" /from ");
                String description = parts[0].substring(6);
                String[] timeParts = parts[1].split(" /to ");
                String from = timeParts[0];
                String to = timeParts[1];
                Task event = new Event(description, from, to);
                tasks[taskCount++] = event;
                System.out.println(horizontalLine);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have " + taskCount + " tasks in the list.\n");
                System.out.println(horizontalLine);
            } else {
                System.out.println(horizontalLine);
                System.out.println("Unknown command.\n");
                System.out.println(horizontalLine);
            }
        }

        scanner.close();
    }
}
