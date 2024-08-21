import java.util.ArrayList;
import java.util.Scanner;

public class Delta {
    private static ArrayList<Task> list = new ArrayList<Task>();

    public static String sayHello() {
        return "\t____________________________________________________________\n"
                + "\t Hello! I'm Delta\n"
                + "\t What can I do for you?\n"
                + "\t____________________________________________________________";
    }

    public static String sayBye() {
        return "\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                + "\t____________________________________________________________";
    }

    public static String addTask(Task task) {
        list.add(task);
        return "\t____________________________________________________________\n"
                + "\t Got it. I've added this task:\n"
                + "\t   " + task.toString() + "\n"
                + "\t Now you have " + list.size() + " tasks in the list.\n"
                + "\t____________________________________________________________";
    }

    public static String markTask(int i) {
        Task task = list.get(i - 1);
        task.markAsDone();
        list.set(i - 1, task);
        return "\t____________________________________________________________\n"
                + "\t Nice! I've marked this task as done:\n"
                + "\t   " + task + "\n"
                + "\t____________________________________________________________";
    }

    public static String unmarkTask(int i) {
        Task task = list.get(i - 1);
        task.markAsNotDone();
        list.set(i - 1, task);
        return "\t____________________________________________________________\n"
                + "\t Ok, I've marked this task as not done yet:\n"
                + "\t   " + task + "\n"
                + "\t____________________________________________________________";
    }

    public static String printTasks() {
        String output = "\t____________________________________________________________\n"
                + "\t Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            output += String.format("\t %d.%s\n", i + 1, list.get(i));
        }
        output += "\t____________________________________________________________";
        return output;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(sayHello());

        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            String output = "";

            try {
                if (task.equals("bye")) {
                    output = sayBye();
                    break;
                } else if (task.equals("list")) {
                    output = printTasks();
                } else if (task.substring(0, 4).equals("mark")) {
                    int taskIdx = Integer.parseInt(task.substring(5));
                    output = markTask(taskIdx);
                } else if (task.substring(0, 6).equals("unmark")) {
                    int taskIdx = Integer.parseInt(task.substring(7));
                    output = unmarkTask(taskIdx);
                } else if (task.substring(0, 4).equals("todo")) {
                    String description = task.substring(5);
                    output = addTask(new Todo(description));
                } else if (task.substring(0, 8).equals("deadline")) {
                    String[] details = task.substring(9).split(" /by ");
                    output = addTask(new Deadline(details[0], details[1]));
                } else if (task.substring(0, 5).equals("event")) {
                    String[] details = task.substring(6).split(" /from ");
                    String[] timings = details[1].split(" /to ");
                    output = addTask(new Event(details[0], timings[0], timings[1]));
                } else {
                    throw new DeltaException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DeltaException e) {
                System.out.println(e.getMessage());
            }
            catch (StringIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            System.out.println(output);
        }

        sc.close();
    }
}
