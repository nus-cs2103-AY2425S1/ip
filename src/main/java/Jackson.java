import java.util.Scanner;
import java.util.ArrayList;

public class Jackson {

    public static String name = "Jackson";
    private static final int EXPECTED_SIZE = 10;
    private static ArrayList<Task> tasks = new ArrayList<>(EXPECTED_SIZE);

    public static void add_list(String taskName) {
        Task t = new Task(taskName);
        System.out.printf("Ya la, adding '%s' to list lah!\n", t);
        tasks.add(t);
    }

    public static void show_list() {
        if (tasks.isEmpty()) {
            System.out.println("Nothing in list lah!");
        } else {
            System.out.println("Here's your list lor!");
            Task curr;
            for (int i = 0; i < tasks.size(); i++) {
                curr = tasks.get(i);
                System.out.printf("%d. %s %s\n", i + 1, curr.getStatus(), curr);
            }
        }
    }

    public static void mark(int index) {
        Task curr = tasks.get(index);
        System.out.printf("Solid lah, I mark %s now\n", curr);
        curr.mark();
        System.out.printf("\t%s %s\n", curr.getStatus(), curr);
    }

    public static void unmark(int index) {
        Task curr = tasks.get(index);
        System.out.printf("Walao, ok la I unmark %s now\n", curr);
        curr.unmark();
        System.out.printf("\t%s %s\n", curr.getStatus(), curr);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Oi! I'm %s!\nWhat you want me do today ah?\n> ", name);
        String[] responses = sc.nextLine().strip().split(" ", 1);
        String command = responses[0];
        int idx;

        while (!command.equals("bye")) {
            switch (command) {
                case "list":
                    show_list();
                    break;
                case "mark":
                    idx = Integer.parseInt(responses[1]);
                    mark(idx - 1);
                    break;
                case "unmark":
                    idx = Integer.parseInt(responses[1]);
                    unmark(idx - 1);
                    break;
                default:
                    add_list(command);
            }
            System.out.print("> ");
            responses = sc.nextLine().strip().split(" ", 0);
            command = responses[0];
        }

        System.out.println("K k, bye lah!");
    }
}
