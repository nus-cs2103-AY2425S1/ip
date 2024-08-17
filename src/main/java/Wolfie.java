import java.util.Scanner;

public class Wolfie {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100]; // array to store tasks
        int taskCount = 0; // counter for number of tasks

        System.out.println("____________________________________________________________");
        System.out.println(" Hello Dean's Lister! I'm Wolfie");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye Dean's Lister. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]); // add number tag and print task
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark ")) {
                String[] words = input.split(" ");
                int taskNumber = Integer.parseInt(words[1]) - 1; // get task number
                tasks[taskNumber].markAsDone(); // mark task as done
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskNumber]); // print marked task
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark ")) {
                String[] words = input.split(" ");
                int taskNumber = Integer.parseInt(words[1]) - 1; // get task number
                tasks[taskNumber].markAsUndone(); // mark task as undone
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as undone:");
                System.out.println("   " + tasks[taskNumber]); // print marked task
                System.out.println("____________________________________________________________");
            } else {
                tasks[taskCount] = new Task(input); // add task to array
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input); // print added task
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}