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
                // response to a task
                Task t = new Task(input);
                tasks.add(t);
                System.out.println("Added '" + input + "' to your task list");
            }

            System.out.print(divider);
        }
    }
}
