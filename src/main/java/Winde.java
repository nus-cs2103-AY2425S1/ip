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

    private final static List<String> reminder = new ArrayList<String>();
    private final static List<Boolean> complete = new ArrayList<Boolean>();

    public static void mark(int i) {
        System.out.print("Nice! I've marked this task as done:\n" + "    ");
        complete.set(i - 1, true);
        task(i);
        System.out.println();
    }

    public static void unmark(int i) {
        System.out.print("OK, I've marked this task as not done yet:\n" + "    ");
        complete.set(i - 1, false);
        task(i);
        System.out.println();
    }

    public static void task(int i) {
        System.out.print((complete.get(i - 1) ? "[X] " : "[ ] ") + reminder.get(i - 1));
    }
    public static void add(String action) {
        reminder.add(action);
        complete.add(false);
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