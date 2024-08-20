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
            if (task.getDescription().equals("bye")) {
                System.out.println(sayBye());
                break;
            } else if (task.getDescription().equals("list")) {
                System.out.println(printTasks());
            } else {
                System.out.println(addTask(task));
            }
        }

        sc.close();
    }
}
