import java.util.Scanner;

public class PandaBot {
    private static void printLine() {
        String line = "_________________________________________";
        System.out.println(line);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // statically storing tasks
        Task[] taskList = new Task[100];
        int taskCount = 0;

        // Simple greeting to the user by PandaBot
        printLine();
        System.out.println("Hello! I'm PandaBot");
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (input.equalsIgnoreCase("list")){
                printLine();
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + taskList[i]);
                }
                printLine();
            } else if (input.startsWith("mark ")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskCount) {
                    taskList[taskNum].markAsDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList[taskNum]);
                    printLine();
                } else {
                    System.out.println("The specified task does not exist");
                }
            } else if (input.startsWith("unmark ")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskCount) {
                    taskList[taskNum].unmark();
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskList[taskNum]);
                    printLine();
                } else {
                    System.out.println("The specified task does not exist");
                }
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();
                taskList[taskCount] = new ToDo(description);
                taskCount++;
                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println(taskList[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                printLine();
            } else if (input.startsWith("deadline ")) {
                String[] details = input.substring(9).split(" /by ");
                if (details.length == 2) {
                    String description = details[0].trim();
                    String by = details[1].trim();
                    taskList[taskCount] = new Deadline(description, by);
                    taskCount++;
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    printLine();
                } else {
                    System.out.println("Invalid format. Use: deadline <description> /by <date>");
                }
            } else if (input.startsWith("event ")){
                String[] details = input.substring(6).split(" /from | /to ");
                if (details.length == 3) {
                    String description = details[0].trim();
                    String from = details[1].trim();
                    String to = details[2].trim();
                    taskList[taskCount] = new Event(description, from, to);
                    taskCount++;
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    printLine();
                } else {
                    System.out.println("Invalid format. Use: event <description> /from <start> /to <end>");
                }
            } else {
                System.out.println("Invalid command. Type '/help' for assistance.");
            }
        }
        scanner.close();
    }
}
