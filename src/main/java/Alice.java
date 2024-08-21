import java.util.*;

public class Alice {
    private static final String NAME = "Alice";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private List<String> tasks;

    public Alice() {
        this.tasks = new ArrayList<>();
    }

    private void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(String.format("> Hello! I'm %s.", NAME));
        System.out.println("> What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private void bye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("> Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    private void echo(String message) {
        // echo user inputs
        System.out.println(HORIZONTAL_LINE);
        System.out.println(String.format("> %s", message));
        System.out.println(HORIZONTAL_LINE);
    }

    private void addTask(String task) {
        tasks.add(task);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(String.format("> added: %s", task));
        System.out.println(HORIZONTAL_LINE);
    }

    private void listTasks() {
        System.out.println(HORIZONTAL_LINE);

        if (tasks.isEmpty()) {
            System.out.println("> no tasks added");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.format(" %d. %s", i + 1, tasks.get(i)));
            }
        }

        System.out.println(HORIZONTAL_LINE);
    }

    private void listen() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String current = scanner.nextLine().trim().toLowerCase();
            if (current.equals("bye")) {
                break;
            } else if (current.equals("list")) {
                listTasks();
            } else {
                addTask(current);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Alice alice = new Alice();

        alice.greet();
        alice.listen();
        alice.bye();
    }
}
