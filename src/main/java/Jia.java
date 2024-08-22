import java.util.ArrayList;
import java.util.Scanner;

public class Jia {
    public static void main(String[] args) {
        //Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        //Create an array list to store tasks
        ArrayList<Task> tasks = new ArrayList<>();
        String input;

        //Greet the user
        System.out.println("______________________________________________________");
        System.out.println("Hello! I'm Jia");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________________");

        //Read user input until they say bye
        do {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("list")) {
                System.out.println("______________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                }
                System.out.println("______________________________________________________");
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                tasks.get(taskNumber).markAsDone();
                System.out.println("______________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + tasks.get(taskNumber));
                System.out.println("______________________________________________________");
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                tasks.get(taskNumber).markAsNotDone();
                System.out.println("______________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + tasks.get(taskNumber));
                System.out.println("______________________________________________________");
            } else if (!input.equalsIgnoreCase("bye")) {
                    tasks.add(new Task(input));
                    System.out.println("______________________________________________________");
                    System.out.println(" " + "added: " + input);
                    System.out.println("______________________________________________________");
            }
        } while (!input.equalsIgnoreCase("bye"));

        //Exit
        System.out.println("______________________________________________________");
        System.out.println(" " + "Bye. Hope to see you again soon!");
        System.out.println("______________________________________________________");

        scanner.close();
    }
}
