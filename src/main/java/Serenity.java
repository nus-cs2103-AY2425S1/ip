import java.util.ArrayList;
import java.util.Scanner;

public class Serenity {
    private static final String horizontalLine = "__________________________________________";
    private static final ArrayList<Task> list = new ArrayList<>();
    private static int count = 0;
    public static void main(String[] args) {

        System.out.println(horizontalLine);
        System.out.println("Hi, I'm Serenity!\n" + "What can I do for you?");
        System.out.println(horizontalLine);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                System.out.println(horizontalLine);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task t = list.get(index);
                t.markAsDone();
                System.out.println(horizontalLine);
                System.out.println("Nice! I've marked this task as done:\n" + t);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task t = list.get(index);
                t.markAsNotDone();
                System.out.println(horizontalLine);
                System.out.println("OK, I've marked this task as not done yet:\n" + t);
            } else if (input.startsWith("todo")) {
                //remove the type of task
                String description = input.split(" ", 2)[1];
                Todo td = new Todo(description);
                addTask(td);
            } else if (input.startsWith("deadline")) {
                String description = input.split(" ", 2)[1];
                String[] parts = description.split("/by");
                Deadline dl = new Deadline(parts[0], parts[1]);
                addTask(dl);
            } else if (input.startsWith("event")) {
                String description = input.split(" ", 2)[1];
                String[] parts = description.split("/from");
                String[] timings = parts[1].split("/to");
                Event e = new Event(parts[0], timings[0], timings[1]);
                addTask(e);
            } else {
                Task t = new Task(input);
                addTask(t);
            }
            System.out.println(horizontalLine);
            input = sc.nextLine();
        }
        sc.close();

        System.out.println(horizontalLine);
        System.out.println("Goodbye. Hope to see you again soon!");
        System.out.println(horizontalLine);

    }

    private static void addTask(Task t) {
        list.add(t);
        count++;
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:\n" + t);
        String numOfTasks = count == 1 ? "task" : "tasks";
        System.out.println("Now you have " + count + " " + numOfTasks + " in the list.");
    }
}
