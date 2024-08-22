import java.util.Scanner;
public class EchoBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm EchoBot");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        label:
        while (true) {
            String userInput = scanner.nextLine();
            String[] inputParts = userInput.split(" ", 2); // Splits input into command and the rest

            String command = inputParts[0];

            switch (command) {
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye! Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break label;
                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + "." + tasks[i]);
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "mark": {
                    int taskNumber = Integer.parseInt(inputParts[1]) - 1;
                    if (taskNumber < taskCount) {
                        tasks[taskNumber].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks[taskNumber]);
                        System.out.println("____________________________________________________________");
                    }
                    break;
                }
                case "unmark": {
                    int taskNumber = Integer.parseInt(inputParts[1]) - 1;
                    if (taskNumber < taskCount) {
                        tasks[taskNumber].markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks[taskNumber]);
                        System.out.println("____________________________________________________________");
                    }
                    break;
                }
                case "todo": {
                    tasks[taskCount] = new Todo(inputParts[1]);
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                }
                case "deadline": {
                    String[] details = inputParts[1].split(" /by ");
                    tasks[taskCount] = new Deadline(details[0], details[1]);
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                }
                case "event": {
                    String[] details = inputParts[1].split(" /from | /to ");
                    tasks[taskCount] = new Event(details[0], details[1], details[2]);
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                }
                default:
                    System.out.println("____________________________________________________________");
                    System.out.println(" I'm sorry, I don't recognize that command.");
                    System.out.println("____________________________________________________________");
                    break;
            }
        }

        scanner.close();
    }
}
