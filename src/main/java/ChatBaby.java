import java.util.ArrayList;
import java.util.Scanner;

public class ChatBaby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        greet();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                printTasks(tasks);
            } else if (input.startsWith("mark")) {
                markTask(tasks, input);
            } else if (input.startsWith("unmark")) {
                unmarkTask(tasks, input);
            } else if (input.startsWith("todo ")) {
                addTodoTask(tasks, input.substring(5));
            } else if (input.startsWith("deadline ")) {
                addDeadlineTask(tasks, input.substring(9));
            } else if (input.startsWith("event ")) {
                addEventTask(tasks, input.substring(6));
            } else {
                addTask(tasks, input);
            }
        }
    }

    public static void greet() {
        System.out.println("____________________________________________________________\n"
                + "Hello! I'm ChatBaby\n"
                + "What can I do for you?\n"
                + "____________________________________________________________");
    }

    public static void bye() {
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________");
    }

    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public static void markTask(ArrayList<Task> tasks, String input) {
        int taskNumber = Integer.parseInt(input.substring(5)) - 1;
        tasks.get(taskNumber).markAsDone();
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + tasks.get(taskNumber).toString()
                + "\n____________________________________________________________");
    }

    public static void unmarkTask(ArrayList<Task> tasks, String input) {
        int taskNumber = Integer.parseInt(input.substring(7)) - 1;
        tasks.get(taskNumber).unMarkAsDone();
        System.out.println("____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n"
                + tasks.get(taskNumber).toString()
                + "\n____________________________________________________________");
    }

    public static void addTask(ArrayList<Task> tasks, String name) {
        tasks.add(new Task(name));
        System.out.println("____________________________________________________________\n"
                + "added: " + name + "\n"
                + "____________________________________________________________");
    }

    public static void addTodoTask(ArrayList<Task> tasks, String name) {
        tasks.add(new ToDo(name));
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + "  " + tasks.get(tasks.size() - 1)
                + "\nNow you have " + tasks.size() + " tasks in the list."
                + "\n____________________________________________________________");
    }

    public static void addDeadlineTask(ArrayList<Task> tasks, String input) {
        String[] parts = input.split(" /by ");
        tasks.add(new Deadline(parts[0], parts[1]));
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + "  " + tasks.get(tasks.size() - 1)
                + "\nNow you have " + tasks.size() + " tasks in the list."
                + "\n____________________________________________________________");
    }

    public static void addEventTask(ArrayList<Task> tasks, String input) {
        String[] parts = input.split(" /from | /to ");
        tasks.add(new Event(parts[0], parts[1], parts[2]));
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + "  " + tasks.get(tasks.size() - 1)
                + "\nNow you have " + tasks.size() + " tasks in the list."
                + "\n____________________________________________________________");
    }
}
