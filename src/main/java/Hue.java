import java.util.Scanner;
public class Hue {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Hue";

        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________" );
        System.out.println("Hello! I'm [" + name + "]");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________" );

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine();

            System.out.println("____________________________________________________________");
            if (input.equals("bye")) {
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                System.out.println("Here aer the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + tasks[taskIndex]);
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].unmarkDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" " + tasks[taskIndex]);
                } else {
                    System.out.println("Invalid task number");
                }
            } else if (!input.equalsIgnoreCase("bye")) {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("added: " + input);
            }
            System.out.println("____________________________________________________________");
        } while (!input.equalsIgnoreCase("bye"));

            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
            scanner.close();


    }

}
