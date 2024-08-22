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
            } else {
                tasks[taskCount] = new Task(command);
                taskCount++;
                System.out.println("     added: " + command);
            }
        }
    }
}
