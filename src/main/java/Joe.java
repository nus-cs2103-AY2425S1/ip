import java.util.Scanner;
public class Joe {
    public static final String horizontalLine = "____________________________________________________________";
    public static final String chatbotName = "Joe";
    public static final String addTaskMessage = "Got it. I've added this task:\n";
    public static final String taskCountMessage = "Now you have %d tasks in the list.\n";

    public static String input = "";
    public static Task[] store = new Task[100];
    public static int currIndex = 0;

    public static void greet() {
        System.out.printf("%s\nHello! I'm %s\nWhat can I do for you?\n%s\n", horizontalLine, chatbotName, horizontalLine);
    }

    public static void farewell() {
        System.out.printf("Bye. Hope to see you again soon!\n%s", horizontalLine);
    }

    public static void handleList(Task[] list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.println(i + 1 + "." + list[i]);
            }
        }
    }

    public static void handleDone(Task[] list, int index) {
        if (list[index] != null) {
            list[index].toggleDone();
            System.out.printf("Nice! I've marked this task as done:\n%s\n", list[index]);
        }
    }

    public static void handleUndone(Task[] list, int index) {
        if (list[index] != null) {
            list[index].toggleDone();
            System.out.printf("Nice! I've marked this task as not done yet:\n%s\n", list[index]);
        }
    }

    public static void handleTodo(String input) {
        String task = input.substring(5);
        if (task.equals("")) {
            System.out.println("Don't expect me to remember nothing!");
            return;
        }
        store[currIndex] = new TaskTodo(task);
        System.out.printf("%s%s\n", addTaskMessage, store[currIndex]);
        currIndex++;
        System.out.printf(taskCountMessage, currIndex);
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
        store[currIndex] = new TaskDeadline(task, by);
        System.out.printf("%s%s\n", addTaskMessage, store[currIndex]);
        currIndex++;
        System.out.printf(taskCountMessage, currIndex);
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
        store[currIndex] = new TaskEvent(task, from, to);
        System.out.printf("%s%s\n", addTaskMessage, store[currIndex]);
        currIndex++;
        System.out.printf(taskCountMessage, currIndex);
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
            else {
                System.out.println("Give me a valid command!");
            }
            System.out.println(horizontalLine);
        }
        farewell();
        scanner.close();
    }
}
