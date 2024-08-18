import java.util.ArrayList;
import java.util.Scanner;

public class Oliver {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Oliver.");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            String command = input.split(" ")[0];

            if (input.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("\t" + (i+1) + ".[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i));
                }
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(index).markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t[X] " + tasks.get(index));
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(index).markAsUndone();
                System.out.println("\tOk, I've marked this task as not done yet:");
                System.out.println("\t[ ] " + tasks.get(index));
            } else {
                Task t = new Task(input);
                tasks.add(t);
                System.out.println("\tadded: " + input);
            }
        }
        sc.close();
    }
}
