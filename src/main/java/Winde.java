import java.util.*;

public class Winde {
    public static void main(String[] args) {
        /* String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

         */
        // System.out.println("Hello from\n" + "Winde");
        greet();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!(input.equals("bye"))) {
            String[] command = input.split(" ");
            if (command[0].equals("list")) {
                list();
            } else if (command [0].equals("mark")) {
                mark(Integer.parseInt(command[1]));
            } else if (command[0].equals("unmark")) {
                unmark(Integer.parseInt(command[1]));
            } else {
                add(input);
            }
            input = scanner.nextLine();
        }

        exit();
    }

    private final static List<Task> reminder = new ArrayList<Task>();

    public static void mark(int i) {
        System.out.print("Nice! I've marked this task as done:\n" + "    ");
        reminder.get(i - 1).mark();
        task(i);
        System.out.println();
    }

    public static void unmark(int i) {
        System.out.print("OK, I've marked this task as not done yet:\n" + "    ");
        reminder.get(i - 1).unmark();
        task(i);
        System.out.println();
    }

    public static void task(int i) {
        System.out.print(reminder.get(i - 1).toString());
    }
    public static void add(String action) {
        Task t = new Task(action);
        reminder.add(t);
        System.out.println("added: " + action);
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= reminder.size(); i++) {
            System.out.print(i + ".");
            task(i);
            System.out.println();
        }
    }

    public static void greet() {
        System.out.println("Hello! I'm Winde\n" + "What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}

class Task {
    protected String action;
    protected boolean complete;

    public Task(String action) {
        this.action = action;
        this.complete = false;
    }

    public String isCompleted() {
        return (complete ? "[X]" : "[ ]"); // mark done task with X
    }

    public void mark() {
        complete = true;
    }

    public void unmark() {
        complete = false;
    }

    @Override
    public String toString() {
        return (complete ? "[X]" : "[ ]") + " " + action;
    }
}