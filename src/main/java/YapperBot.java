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
            } else {
                tasks[numTask] = new Task(userInput);
                numTask++;
                System.out.println("________________________________");
                System.out.println("added: " + userInput);
                System.out.println("________________________________");
            }
        }

        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
}
