import java.util.Scanner;

public class Angel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        boolean[] isDone = new boolean[100];
        int taskCount = 0;

        String logo = "____________________________________________________________\n";

        System.out.println(logo + " Hello! I'm Angel\n What can I do for you?\n" + logo);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(logo + " Bye. Hope to see you again soon!\n" + logo);
                break;
            } else if (userInput.equals("list")) {
                System.out.println(logo + " Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    String status = isDone[i] ? "[X]" : "[ ]";
                    System.out.println(" " + (i + 1) + "." + tasks[i].replace("[ ]", status));
                }
                System.out.println(logo);
            } else if (userInput.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    isDone[taskIndex] = true;
                    System.out.println(logo + " Nice! I've marked this task as done:\n"
                            + "   " + tasks[taskIndex].replace("[ ]", "[X]") + "\n" + logo);
                }
            } else if (userInput.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    isDone[taskIndex] = false;
                    System.out.println(logo + " OK, I've marked this task as not done yet:\n"
                            + "   " + tasks[taskIndex].replace("[X]", "[ ]") + "\n" + logo);
                }
            } else if (userInput.startsWith("todo ")) {
                String description = userInput.substring(5);
                tasks[taskCount] = "[T][ ] " + description;
                isDone[taskCount] = false;
                taskCount++;
                System.out.println(logo + " Got it. I've added this task:\n"
                        + "   " + tasks[taskCount - 1] + "\n"
                        + " Now you have " + taskCount + " tasks in the list.\n" + logo);
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ");
                if (parts.length == 2) {
                    String description = parts[0];
                    String by = parts[1];
                    tasks[taskCount] = "[D][ ] " + description + " (by: " + by + ")";
                    isDone[taskCount] = false;
                    taskCount++;
                    System.out.println(logo + " Got it. I've added this task:\n"
                            + "   " + tasks[taskCount - 1] + "\n"
                            + " Now you have " + taskCount + " tasks in the list.\n" + logo);
                } else {
                    System.out.println(logo + " Invalid deadline format. Please use the format 'deadline <description> /by <date/time>'.\n" + logo);
                }
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                if (parts.length == 3) {
                    String description = parts[0];
                    String from = parts[1];
                    String to = parts[2];
                    tasks[taskCount] = "[E][ ] " + description + " (from: " + from + " to: " + to + ")";
                    isDone[taskCount] = false;
                    taskCount++;
                    System.out.println(logo + " Got it. I've added this task:\n"
                            + "   " + tasks[taskCount - 1] + "\n"
                            + " Now you have " + taskCount + " tasks in the list.\n" + logo);
                } else {
                    System.out.println(logo + " Invalid event format. Please use the format 'event <description> /from <start> /to <end>'.\n" + logo);
                }
            } else {
                System.out.println(logo + " Sorry, I don't recognize that command.\n" + logo);
            }
        }

        scanner.close();
    }
}
