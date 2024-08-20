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
                + "\t_____________________________________________________________";
    }

    public static String addTask(Task task) {
        list.add(task);
        return "\t____________________________________________________________\n"
                + "\t added: " + task.getDescription() + "\n"
                + "\t_____________________________________________________________";
    }

    public static String markTask(int i) {
        Task task = list.get(i - 1);
        task.markAsDone();
        list.set(i - 1, task);
        return "\t____________________________________________________________"
                + "\t Nice! I've marked this task as done:\n"
                + "\t\t[" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + "\t_____________________________________________________________";
    }

    public static String unmarkTask(int i) {
        Task task = list.get(i - 1);
        task.markAsNotDone();
        list.set(i - 1, task);
        return "\t____________________________________________________________"
                + "\t Ok, I've marked this task as not done yet:\n"
                + "\t\t[" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + "\t_____________________________________________________________";
    }

    public static String printTasks() {
        String output = "\t____________________________________________________________\n"
                + "\t Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            output += String.format("\t %d.[%s] %s\n", i + 1, list.get(i).getStatusIcon(), list.get(i).getDescription());
        }
        output += "\t_____________________________________________________________";
        return output;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(sayHello());

        while (sc.hasNextLine()) {
            Task task = new Task(sc.nextLine());
            String taskName = task.getDescription();
            if (taskName.equals("bye")) {
                System.out.println(sayBye());
                break;
            } else if (taskName.equals("list")) {
                System.out.println(printTasks());
            } else if (taskName.substring(0, 4).equals("mark")) {
                System.out.println(markTask(Integer.parseInt(taskName.substring(5))));
            } else if (taskName.substring(0, 6).equals("unmark")) {
                System.out.println(unmarkTask(Integer.parseInt(taskName.substring(7))));
            } else {
                System.out.println(addTask(task));
            }
        }

        sc.close();
    }
}
