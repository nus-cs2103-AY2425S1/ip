import java.util.ArrayList;
import java.util.Scanner;

public class Eevee {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String divider = "____________________________________________________________\n";
        String greeting = "Hello! I'm Eevee\nWhat can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        System.out.print(divider + greeting + divider);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();
            System.out.print(divider);

            if (input.equals("bye")) {
                // response to command 'bye'
                System.out.println(exit);
                break;
            } else if (input.equals("list")) {
                // response to command 'list'
                System.out.println("Here are your tasks:");
                tasks.forEach((task) ->
                    System.out.println((tasks.indexOf(task) + 1) + ". " + task)
                );
            } else if (input.equals("mark")) {
                // response to command 'mark'
                int taskNumber = scanner.nextInt();
                Task t = tasks.get(taskNumber - 1);
                t.markAsDone();
                System.out.println("Congratulations! I've marked the following task as done:\n  " + t);
            } else if (input.equals("unmark")) {
                // response to command 'unmark'
                int taskNumber = scanner.nextInt();
                Task t = tasks.get(taskNumber - 1);
                t.unmarkAsDone();
                System.out.println("Ok! Task no longer marked as done:\n  " + t);
            } else {
                String s = scanner.nextLine();
                if (input.equals("todo")) {
                    // response to command for T task
                    Todo t = new Todo(s);
                    tasks.add(t);
                    System.out.println("Added the following task to your list:\n" + t);
                }

                if (input.equals("deadline")) {
                    // response to command for D task
                    String[] info = s.split("/", 2);
                    Deadline d = new Deadline(info[0], info[1]);
                    tasks.add(d);
                    System.out.println("Added the following task to your list:\n" + d);
                }

                if (input.equals("event")) {
                    // response to command for E task
                    String[] info = s.split("/", 3);
                    Event e = new Event(info[0], info[1], info[2]);
                    tasks.add(e);
                    System.out.println("Added the following task to your list:\n" + e);
                }
                System.out.println("You now have " + tasks.size() + " task(s).");

            }

            System.out.print(divider);
        }
    }
}
