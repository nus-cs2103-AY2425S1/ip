import java.util.Scanner;

public class YapperBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numTask = 0;

        System.out.println("________________________________");
        System.out.println("Hello! I'm YapperBot");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                System.out.println("________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numTask; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("________________________________");
            } else if (userInput.startsWith("mark")) {
                int targetTask = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[targetTask].mark();
                System.out.println("________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[targetTask]);
                System.out.println("________________________________");
            } else if (userInput.startsWith("unmark")) {
                int targetTask = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[targetTask].unmark();
                System.out.println("________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[targetTask]);
                System.out.println("________________________________");
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5);
                tasks[numTask] = new Todo(description);
                numTask++;
                System.out.println("________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[numTask - 1]);
                System.out.println("Now you have " + numTask + (numTask == 1 ? " task" : " tasks") + " in the list.");
                System.out.println("________________________________");
            } else if (userInput.startsWith("deadline")) {
                String[] input = userInput.substring(9).split(" /by ");
                String description = input[0];
                String by = input[1];
                tasks[numTask] = new Deadline(description, by);
                numTask++;
                System.out.println("________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[numTask - 1]);
                System.out.println("Now you have " + numTask + (numTask == 1 ? " task" : " tasks") + " in the list.");
                System.out.println("________________________________");
            } else if (userInput.startsWith("event")) {
                String[] input = userInput.substring(6).split(" /from | /to ");
                String description = input[0];
                String from = input[1];
                String to = input[2];
                tasks[numTask] = new Event(description, from, to);
                numTask++;
                System.out.println("________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[numTask - 1]);
                System.out.println("Now you have " + numTask + (numTask == 1 ? " task" : " tasks") + " in the list.");
                System.out.println("________________________________");
            }
        }

        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
}
