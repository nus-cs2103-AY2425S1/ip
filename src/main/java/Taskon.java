import java.util.ArrayList;
import java.util.Scanner;

public class Taskon {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String description = scanner.nextLine();
            if (description.equals("bye")) {
                exit();
                break;
            } else if (description.equals("list")) {
                listItems(tasks);
                System.out.println("\n");
            } else if (description.contains("unmark")) {
                int index = description.charAt(7) - '0';
                tasks.get(index - 1).markAsUndone();
                uncompleted(tasks.get(index - 1));
            } else if (description.contains("mark")) {
                int index = description.charAt(5) - '0';
                tasks.get(index - 1).markAsDone();
                completed(tasks.get(index - 1));
            } else {
                Task t;
                if (description.startsWith("todo")) {
                    t = new Todo(description.substring(5));
                } else if (description.startsWith("deadline")) {
                    String by = description.substring(description.indexOf("/by") + 4);
                    String substring = description.substring(9, description.indexOf("/by"));
                    t = new Deadline(substring, by);
                } else {
                    int start = description.indexOf("/from") + 6;
                    int end = description.indexOf("/to") + 4;
                    String from = description.substring(start, description.indexOf("/to")-1);
                    String to = description.substring(end);
                    String substring = description.substring(6, description.indexOf("/from")-1);
                    t = new Event(substring, from, to);
                }
                tasks.add(t);
                System.out.println("Got it! I've added this task:\n    " + t + "\nNow you have " +
                        tasks.size() + " tasks in your list.\n");
            }
        }
    }

    public static void greet() {
        String greeting = "Hello! I'm Taskon\nWhat can I do for you?\n";
        System.out.println(greeting);
    }

    public static void exit() {
        String exiting = "Bye. Hope to see you again soon!\n";
        System.out.println(exiting);
    }

    public static void completed(Task task) {
        String complete = "Woohoo! Task complete! \nI've marked this as done:\n";
        System.out.println(complete + task.toString());
    }

    public static void uncompleted(Task task) {
        String uncompleted = "Got it! No rush, I've marked it as not done yet:\n";
        System.out.println(uncompleted + task.toString());
    }

    public static void listItems(ArrayList<Task> tasks) {
        System.out.println("Here's what we've got on your to-do list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(i+1 + "." + t.toString());
        }
    }
}
