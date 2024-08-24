import java.util.InputMismatchException;
import java.util.Scanner;

public class Lemon {
    /*** Initialising ***/
    String logoMsg = "____________________________________________________________\n"
            + " Hello! I'm Lemon\n"
            + " What can I do for you?\n";
    String endMsg = " Bye. Hope to see you again soon!\n"
            + "____________________________________________________________\n";
    String barMsg = "____________________________________________________________";
    String emptyListMsg = " Sowwy, theres currently no tasks in your list.\n Ill be happy to add some for you OwO!";
    String listMsg = " Here are the tasks in your list:";
    String markMsg = " Nice! I've marked this task as done:";
    String unmarkMsg = " OK, I've marked this task as not done yet:";
    String addTaskMsg = " Got it. I've added this task:";

    Task[] tasks = new Task[100];
    int numTasks = 0;
    public static void main(String[] args) {
        Lemon lemon = new Lemon();
        Scanner scan = new Scanner(System.in);
        String input = null;

        /*** Program starts here ***/
        System.out.print(lemon.logoMsg);

        while (true) {
            System.out.println(lemon.barMsg);

            input = scan.next();
            System.out.println(lemon.barMsg);
            try {
                if (input.equals("bye") || input.equals("Bye")) {
                    break;
                } else if (input.equals("list") || input.equals("List")) {
                    if (lemon.numTasks == 0) System.out.println(lemon.emptyListMsg);
                    else {
                        System.out.println(lemon.listMsg);
                        lemon.printList();
                    }
                } else if (input.equals("mark") || input.equals("Mark")) {
                    int next = scan.nextInt();
                    if (next > lemon.numTasks) {
                        throw new InvalidCommandException(" OOPS!!! Please select a valid task");
                    }

                    System.out.println(lemon.markMsg);
                    lemon.tasks[next - 1].markDone();

                    System.out.println("   " + lemon.tasks[next - 1].toString());
                } else if (input.equals("unmark") || input.equals("Unmark")) {
                    int next = scan.nextInt();
                    if (next > lemon.numTasks) {
                        throw new InvalidCommandException(" OOPS!!! Please select a valid task");
                    }

                    System.out.println(lemon.unmarkMsg);
                    lemon.tasks[next - 1].unmarkDone();

                    System.out.println("   " + lemon.tasks[next - 1].toString());
                } else if (input.equals("todo") || input.equals("TODO")) {
                    String next = scan.nextLine();

                    lemon.addNewTask(new Todo(next));
                } else if (input.equals("deadline") || input.equals("Deadline")) {
                    String[] next = scan.nextLine().split("/by ");

                    lemon.addNewTask(new Deadline(next[0], next[1]));
                } else if (input.equals("event") || input.equals("Event")) {
                    String[] next = scan.nextLine().split("/from ");

                    lemon.addNewTask(new Event(next[0], next[1]));
                } else {
                    throw new InvalidCommandException(" OOPS!!! I'm sowwy, but I don't know what that means :-(\n\n" +
                            " I can help you add tasks with \"todo\", \"deadline\", \"event\"\n" +
                            " I can also keep track of all your tasks with \"list\"\n" +
                            " If you wanna update certain tasks, use \"mark\" or \"unmark\" and then its number\n");
                }
            } catch (InvalidCommandException | DescriptionException e) {
                System.out.println(e.getMessage());
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(" OOPS!!! I cant understand your mark/unmark, please select using numbers xd");
                scan.nextLine();
            }
        }

        System.out.println(lemon.endMsg);
    }

    private void printList() {
        for (int i = 0; i < numTasks; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i].toString());
        }
    }

    private void addNewTask(Task t) throws DescriptionException {
        if (t.description.isEmpty() || t.description.equals(" "))
            throw new DescriptionException(" OOPS!!! The description of a " + t.type + " cannot be empty");
        System.out.println(addTaskMsg);

        tasks[numTasks] = t;
        numTasks++;

        System.out.println("   " + t.toString());
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
    }
}
