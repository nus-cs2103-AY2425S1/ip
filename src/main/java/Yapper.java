import java.util.Scanner;

public class Yapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Yapper");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();
            String[] userInputParts = userInput.split(" ", 2);
            String command = userInputParts[0];

            if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (command.equals("mark")) {
                int taskNumber = Integer.parseInt(userInputParts[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskNumber]);
                System.out.println("____________________________________________________________");
            } else if (command.equals("unmark")) {
                int taskNumber = Integer.parseInt(userInputParts[1]) - 1;
                tasks[taskNumber].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[taskNumber]);
                System.out.println("____________________________________________________________");
            } else if (command.equals("todo")) {
                Task task = new Todo(userInputParts[1]);
                tasks[taskCount] = task;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (command.equals("deadline")) {
                String[] details = userInputParts[1].split(" /by ");
                Task task = new Deadline(details[0], details[1]);
                tasks[taskCount] = task;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (command.equals("event")) {
                String[] details = userInputParts[1].split(" /from ");
                String[] fromTo = details[1].split(" /to ");
                Task task = new Event(details[0], fromTo[0], fromTo[1]);
                tasks[taskCount] = task;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Sorry, I don't recognize that command.");
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
