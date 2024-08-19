import java.util.Scanner;
import java.util.ArrayList;
public class Joe {
    public static final String horizontalLine = "____________________________________________________________";
    public static final String chatbotName = "Joe";
    public static final String addTaskMessage = "Got it. I've added this task:\n";
    public static final String taskCountMessage = "Now you have %d tasks in the list.\n";

    public static String input = "";
    public static ArrayList<Task> store = new ArrayList<>();

    public static void greet() {
        System.out.printf("%s\nHello! I'm %s\nWhat can I do for you?\n%s\n", horizontalLine, chatbotName, horizontalLine);
    }

    public static void farewell() {
        System.out.printf("Bye. Hope to see you again soon!\n%s", horizontalLine);
    }

    public static void handleList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + "." + list.get(i));
        }
    }

    public static void handleDone(ArrayList<Task> list, int index) {
        list.get(index).toggleDone();
        System.out.printf("Nice! I've marked this task as done:\n%s\n", list.get(index));
    }

    public static void handleUndone(ArrayList<Task> list, int index) {
        list.get(index).toggleDone();
        System.out.printf("Nice! I've marked this task as not done yet:\n%s\n", list.get(index));
    }

    public static void handleDelete(ArrayList<Task> list, int index) {
        System.out.printf("Noted. I've removed this task:\n%s\n", list.get(index));
        list.remove(index);
        System.out.printf(taskCountMessage, list.size());
    }

    public static void handleTodo(String input) {
        String task = input.substring(5);
        if (task.equals("")) {
            System.out.println("Don't expect me to remember nothing!");
            return;
        }
        store.add(new TaskTodo(task));
        System.out.printf("%s%s\n", addTaskMessage, store.getLast());
        System.out.printf(taskCountMessage, store.size());
    }

    public static void handleDeadline(String input) {
        int byIndex = input.indexOf("/by ");
        if (byIndex == -1) {
            System.out.println("BY WHEN??!!");
            return;
        }
        String task = input.substring(9, byIndex - 1);
        if (task.equals("")) {
            System.out.println("Don't expect me to remember nothing!");
            return;
        }
        String by = input.substring(byIndex + 4);
        store.add(new TaskDeadline(task, by));
        System.out.printf("%s%s\n", addTaskMessage, store.getLast());
        System.out.printf(taskCountMessage, store.size());
    }

    public static void handleEvent(String input) {
        int fromIndex = input.indexOf("/from ");
        int toIndex = input.indexOf("/to ");
        if (fromIndex == -1 || toIndex == -1) {
            System.out.println("Give me a valid from and to!");
            return;
        }
        String task = input.substring(6, fromIndex - 1);
        if (task.equals("")) {
            System.out.println("Don't expect me to remember nothing!");
            return;
        }
        String from = input.substring(fromIndex + 6, toIndex - 1);
        String to = input.substring(toIndex + 4);
        store.add(new TaskEvent(task, from, to));
        System.out.printf("%s%s\n", addTaskMessage, store.getLast());
        System.out.printf(taskCountMessage, store.size());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        greet();
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            System.out.println(horizontalLine);
            if (input.equals("bye")) {
                break;
            }
            else if (input.equals("list")) {
                handleList(store);
            }
            else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                handleDone(store, index);
            }
            else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                handleUndone(store, index);
            }
            else if (input.startsWith("todo")) {
                handleTodo(input);
            }
            else if (input.startsWith("deadline")) {
                handleDeadline(input);
            }
            else if (input.startsWith("event")) {
                handleEvent(input);
            }
            else if (input.startsWith("delete")) {
                handleDelete(store, Integer.parseInt(input.split(" ")[1]) - 1);
            }
            else {
                System.out.println("Give me a valid command!");
            }
            System.out.println(horizontalLine);
        }
        farewell();
        scanner.close();
    }
}
