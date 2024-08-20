import java.util.Scanner;

public class Peter {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Peter\nWhat can I do for you?\n");

        // Init Globals
        Task[] tasks = new Task[100];
        int lastIndex = 0;
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().strip();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < lastIndex; i++) {
                    Task t = tasks[i];
                    System.out.println("Here are the tasks in your list: ");
                    System.out.println((i + 1) + String.format(".%s ", t.toString()));
                }
            } else if (command.contains("unmark")) {
                // extract integer value
                String intValue = command.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(intValue);

                Task t = tasks[index - 1];
                t.markAsNotDone();

                System.out.println("OK! I've marked the task as not done yet: ");
                System.out.println(t.toString());
            } else if (command.contains("mark")) {
                // extract integer value
                String intValue = command.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(intValue);

                Task t = tasks[index - 1];
                t.markAsDone();

                System.out.println("Good job! I've marked this task as done: ");
                System.out.println(t.toString());
            } else {
                tasks[lastIndex++] = new Task(command);
                System.out.println("added: " + command);
            }

            command = scanner.nextLine();
        }

        // EXIT
        System.out.println("\nBye, hope to see you again soon!");
        scanner.close();
    }
}
