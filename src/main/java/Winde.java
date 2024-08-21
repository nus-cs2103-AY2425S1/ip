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
            if (input.equals("list")) {
                list();
            } else {
                add(input);
            }
            input = scanner.nextLine();
        }

        exit();
    }

    private static List<String> reminder = new ArrayList<String>();

    public static void add(String action) {
        reminder.add(action);
        System.out.println("added: " + action);
    }

    public static void list() {
        for (int i = 1; i <= reminder.size(); i++) {
            System.out.println(i + ". " + reminder.get(i - 1));
        }
    }

    public static void greet() {
        System.out.println("Hello! I'm Winde\n" + "What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
