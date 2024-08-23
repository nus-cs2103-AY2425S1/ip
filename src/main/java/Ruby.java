import java.util.Scanner;

public class Ruby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        String greeting = """
                Hello! I'm Ruby
                What can I do for you?
                """;
        System.out.println(greeting);

        while (true) {
            String command = scanner.nextLine().trim();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks[i]);
                }
            } else if (command.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(command.substring(5)) - 1;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].markAsDone();
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + tasks[taskNumber]);
                } else {
                    System.out.println("     Invalid task number.");
                }
            } else if (command.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(command.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].markAsNotDone();
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("       " + tasks[taskNumber]);
                } else {
                    System.out.println("     Invalid task number.");
                }
            } else if (command.startsWith("todo ")) {
                String description = command.substring(5);
                tasks[taskCount] = new Todo(description);
                taskCount++;
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + tasks[taskCount - 1]);
                System.out.println("     Now you have " + taskCount + " tasks in the list.");
            } else if (command.startsWith("deadline ")) {
                String[] parts = command.substring(9).split(" /by ");
                String description = parts[0];
                String by = parts[1];
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + tasks[taskCount - 1]);
                System.out.println("     Now you have " + taskCount + " tasks in the list.");
            } else if (command.startsWith("event ")) {
                String[] parts = command.substring(6).split(" /from | /to ");
                String description = parts[0];
                String from = parts[1];
                String to = parts[2];
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + tasks[taskCount - 1]);
                System.out.println("     Now you have " + taskCount + " tasks in the list.");
            } else {
                System.out.println("     I'm sorry, I don't understand that command.");
            }
        }
        scanner.close();
    }
}
