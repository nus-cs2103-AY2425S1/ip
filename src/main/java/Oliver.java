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
                    System.out.println("\t" + (i+1) + "." + tasks.get(i));
                }
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(index).markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t" + tasks.get(index));
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(index).markAsUndone();
                System.out.println("\tOk, I've marked this task as not done yet:");
                System.out.println("\t" + tasks.get(index));
            } else if (command.equals("todo")) {
                ToDo t = new ToDo(input.split(" ", 2)[1]);
                tasks.add(t);
                addSuccess(t, tasks);
            } else if (command.equals("deadline")) {
                String[] parts = input.split("/by ");
                String time = parts[1];
                String action = parts[0].trim();
                Deadline d = new Deadline(action.split(" ", 2)[1], time);
                tasks.add(d);
                addSuccess(d, tasks);
            } else if (command.equals("event")) {
                String[] parts = input.split("/from |/to ");
                String action = parts[0].trim();
                String start = parts[1].trim();
                String end = parts[2].trim();
                Event e = new Event(action.split(" ", 2)[1], start, end);
                tasks.add(e);
                addSuccess(e, tasks);
            } else {
                System.out.println("Invalid input.");
            }
        }
        sc.close();
    }

    public static void addSuccess(Task t, ArrayList<Task>tasks) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }
}
