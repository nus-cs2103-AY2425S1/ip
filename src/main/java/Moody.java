import java.util.Scanner;

public class Moody {
    public static void main(String[] args) {
        String spacer = "____________________________________________________________\n";
        String indent = "    ";

        System.out.println(spacer
                + "Hello! I'm Moody!\nWhat can I do for you?\n"
                + spacer);

        Scanner scanner = new Scanner(System.in);
        String userInput;
        Task[] userTasks = new Task[100];
        int taskCount = 0;

        while (true) {
            userInput = scanner.nextLine().trim();
            if (userInput.equals("bye")) {
                System.out.println(spacer
                        + "Bye. Hope to see you again soon!\n"
                        + spacer);
                break;
            } else if (userInput.equals("list")) {
                System.out.print(spacer);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + userTasks[i]);
                }
                System.out.println(spacer);
            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                userTasks[taskNumber].markAsDone();
                System.out.println(spacer
                        + "Nice! I've marked this task as done:");
                System.out.println(indent
                        + userTasks[taskNumber].toString());
                System.out.println(spacer);
            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                userTasks[taskNumber].markAsNotDone();
                System.out.println(spacer
                        + "OK, I've marked this task as not done yet:");
                System.out.println(indent
                        + userTasks[taskNumber].toString());
                System.out.println(spacer);
            } else if (userInput.startsWith("todo ")) {
                String description = userInput.substring(5).trim();
                if (!description.isEmpty()) {
                    userTasks[taskCount] = new Todo(description);
                    taskCount++;
                    System.out.println(spacer
                            + "Got it. I've added this task:\n"
                            + indent + userTasks[taskCount - 1] + "\n"
                            + "Now you have " + taskCount + " tasks in the list.\n"
                            + spacer);
                }
            } else if (userInput.startsWith("deadline ")){
                String[] substrings = userInput.substring(9).split(" /by ");
                // ensure user inputted task name and deadline
                if (substrings.length == 2) {
                    userTasks[taskCount] = new Deadline(substrings[0].trim(), substrings[1].trim());
                    taskCount++;
                    System.out.println(spacer
                            + "Got it. I've added this task:\n"
                            + indent + userTasks[taskCount - 1] + "\n"
                            + "Now you have " + taskCount + " tasks in the list.\n"
                            + spacer);
                }
            } else if (userInput.startsWith("event ")) {
                String[] substrings = userInput.substring(5).split(" /from | /to ");
                // ensure user inputted task name, from date, to date
                if (substrings.length == 3) {
                    userTasks[taskCount] = new Event(substrings[0].trim(), substrings[1].trim(), substrings[2].trim());
                    taskCount++;
                    System.out.println(spacer
                            + "Got it. I've added this task:\n"
                            + indent + userTasks[taskCount - 1] + "\n"
                            + "Now you have " + taskCount + " tasks in the list.\n"
                            + spacer);
                }
            } else {
                System.out.println(spacer +
                        "Command not found. Please try again.\n"
                        + spacer);
            }
        }
        scanner.close();
    }
}
