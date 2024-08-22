import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class PHamBot {
    private static final String line = "____________________________________________________________\n";
    private static TaskList tasks = new TaskList();

    public static void main(String[] args) {
        Greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                SayGoodbye();
                break;
            }
            if (input.equals("list")) {
                ListTasks();
            }
            if (input.contains("task")) {
                String task = input.substring(5);
                System.out.println(OutlineMessage(AddTask(task)));
            }
            if (input.contains("unmark")) {
                unmark(Integer.parseInt(input.substring(7)));
            } else if (input.contains("mark")) {
                mark(Integer.parseInt(input.substring(5)));
            }


        }
    }

    private static String OutlineMessage(String msg) {
        return line + msg + "\n" + line;
    }

    public static void Greet() {
        String greeting = "Hi! I'm PHamBot\nHappy to be of service to you today!";
        System.out.println(OutlineMessage(greeting));
    }

    public static void SayGoodbye() {
        String goodbye = "Hope I was able to help\nGoodbye!";
        System.out.println(OutlineMessage(goodbye));
    }

    public static String AddTask(String task) {
        tasks.addTask(new Task(task));
        return "Added: " + task;
    }
    public static void ListTasks() {
        System.out.println(OutlineMessage(tasks.toString()));
    }

    public static boolean mark(int index) {
        int i = index - 1;
        tasks.markTask(i);
        System.out.println(OutlineMessage("I've marked the following task as done!\n" + tasks.getTask(i)));
        return true;
    }

    public static boolean unmark(int index) {
        int i = index - 1;
        tasks.unmarkTask(i);
        System.out.println(OutlineMessage("I've marked the following task as not done.\n" + tasks.getTask(i)));
        return true;
    }
}
