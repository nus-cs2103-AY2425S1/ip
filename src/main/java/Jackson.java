import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Jackson {

    public static String name = "Jackson";
    public static enum TASK {
        TODO, EVENT, DEADLINE;
    }
    private static final int EXPECTED_SIZE = 100;
    private static ArrayList<Task> tasks = new ArrayList<>(EXPECTED_SIZE);

    public static void add_list(Task task) {
        System.out.println("Ya la, adding this task to your list!");
        tasks.add(task);
        System.out.printf("\t%s\n", task);
        System.out.printf("You now got %s tasks in your list leh\n", tasks.size());
    }

    public static void show_list() {
        if (tasks.isEmpty()) {
            System.out.println("Nothing in list lah!");
        } else {
            System.out.println("Here's your list lor!");
            Task curr;
            for (int i = 0; i < tasks.size(); i++) {
                curr = tasks.get(i);
                System.out.printf("%d. %s\n", i + 1, curr);
            }
        }
    }

    public static void mark(int index) {
        Task curr = tasks.get(index);
        System.out.println("Solid lah, marked already");
        curr.mark();
        System.out.printf("\t%s\n", curr);
    }

    public static void unmark(int index) {
        Task curr = tasks.get(index);
        System.out.println("Walao, ok la I unmark already...");
        curr.unmark();
        System.out.printf("\t%s\n", curr);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Oi! I'm %s!\nWhat you want me do today ah?\n> ", name);
        String response = sc.nextLine().strip();
        String suffix;
        String[] splitted;

        while (!response.equals("bye")) {
            if (response.startsWith("list")) {
                show_list();
            } else if (response.startsWith("todo")) {
                suffix = response.split(" ", 2)[1];
                Task t = new Todo(suffix.strip());
                add_list(t);
            } else if (response.startsWith("deadline")) {
                suffix = response.split(" ", 2)[1];
                splitted = suffix.split("/by", 0);

                Task t = new Deadline(splitted[0].strip(), splitted[1].strip());
                add_list(t);
            } else if (response.startsWith("event")) {
                suffix = response.split(" ", 2)[1];
                splitted = suffix.split("(/from)|(/to)", 0);
                Task t = new Event(splitted[0].strip(), splitted[1].strip(), splitted[2].strip());
                add_list(t);
            } else if (response.startsWith("mark")) {
                suffix = response.split(" ", 2)[1];
                mark(Integer.parseInt(suffix) - 1);
            } else if (response.startsWith("unmark")) {
                suffix = response.split(" ", 2)[1];
                unmark(Integer.parseInt(suffix) - 1);
            } else {
                System.out.println("Har? What you talking leh?");
            }
            System.out.print("> ");
            response = sc.nextLine().strip();
        }

        System.out.println("K k, bye lah!");
    }
}
