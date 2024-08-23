import java.util.Scanner;

public class Lemon {
    public static void main(String[] args) {
        /*** Initialising ***/
        String logo = "____________________________________________________________\n"
                + " Hello! I'm Lemon\n"
                + " What can I do for you?\n";
        String end = " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        String bar = "____________________________________________________________";
        String list = " Here are the tasks in your list:";
        String mark = " Nice! I've marked this task as done:";
        String unmark = " OK, I've marked this task as not done yet:";
        String addTask = " Got it. I've added this task:";

        Scanner scan = new Scanner(System.in);
        String input = null;
        Task[] tasks = new Task[100];
        int numTasks = 0;

        String numTaskAdded = " Now you have " + numTasks + " tasks in the list.";

        /*** Program starts here ***/
        System.out.print(logo);

        while (true) {
            System.out.println(bar);

            input = scan.next();
            System.out.println(bar);
            if (input.equals("bye") || input.equals("Bye")) {
                break;
            } else if (input.equals("list") || input.equals("List")) {
                System.out.println(list);
                for (int i = 0; i < numTasks; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i].toString());
                }
            } else if (input.equals("mark") || input.equals("Mark")) {
                System.out.println(mark);

                int next = scan.nextInt();
                tasks[next-1].markDone();

                System.out.println("   " + tasks[next-1].toString());
            } else if (input.equals("unmark") || input.equals("Unmark")) {
                System.out.println(unmark);

                int next = scan.nextInt();
                tasks[next-1].unmarkDone();

                System.out.println("   " + tasks[next-1].toString());
            } else if (input.equals("todo") || input.equals("TODO")) {
                System.out.println(addTask);

                String next = scan.nextLine();
                Task t = new Todo(next);

                tasks[numTasks] = t;
                numTasks++;

                System.out.println("   " + t.toString());
                System.out.println(" Now you have " + numTasks + " tasks in the list.");
            } else if (input.equals("deadline") || input.equals("Deadline")) {
                System.out.println(addTask);

                String[] next = scan.nextLine().split("/by");

                Task t = new Deadline(next[0], next[1]);

                tasks[numTasks] = t;
                numTasks++;

                System.out.println("   " + t.toString());
                System.out.println(" Now you have " + numTasks + " tasks in the list.");
            } else if (input.equals("event") || input.equals("Event")) {
                System.out.println(addTask);

                String[] next = scan.nextLine().split("/from");

                Task t = new Event(next[0], next[1]);

                tasks[numTasks] = t;
                numTasks++;

                System.out.println("   " + t.toString());
                System.out.println(" Now you have " + numTasks + " tasks in the list.");
            } else {
                System.out.println(addTask);

                String next = scan.nextLine();
                Task t = new Todo(input + next);

                tasks[numTasks] = t;
                numTasks++;

                System.out.println("   " + t.toString());
                System.out.println(" Now you have " + numTasks + " tasks in the list.");
            }
        }

        System.out.println(end);
    }
}
