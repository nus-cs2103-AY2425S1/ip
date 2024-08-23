import java.util.Scanner;

public class MySutong {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100]; // Array to store tasks
        int taskCount = 0; // Counter to keep track of the number of tasks

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm MySutong");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        String input;
        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(horizontalLine);
            } else {
                tasks[taskCount] = input;
                taskCount = taskCount + 1;
                System.out.println(horizontalLine);
                System.out.println("added: " + input);
                System.out.println(horizontalLine);
            }
        }

        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);

        scanner.close();
    }
}
