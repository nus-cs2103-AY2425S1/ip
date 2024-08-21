import java.util.Scanner;

public class Hana {
    public static void main(String[] args) {
        String line = "    ____________________________________________________________\n";
        String greeting = "    Hello I'm Hana\n" +
                "    What can I do for you?\n";
        String closing = "    Bye. Hope to see you again soon!\n";
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        Task[] tasks = new Task[100];
        int numberOfTasks = 0;

        System.out.println(line + greeting + line);
        String input = scanner.nextLine();  // Read user input
        String output = "";
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(line);
            }
            else {
                Task task = new Task(input);
                tasks[numberOfTasks] = task;
                numberOfTasks++;
                output = "    added: " + input + "\n";
                System.out.println(line + output + line);
            }
            input = scanner.nextLine();
        }
        System.out.println(line + closing + line);
    }
}
