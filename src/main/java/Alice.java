import java.util.*;
import task.*;

public class Alice {
    private static final String NAME = "Alice";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private final List<Task> tasks;

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

    private void addTask(Task task) {
        tasks.add(task);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("> Got it. I've added this task:");
        System.out.println(String.format("\t%s", task));
        System.out.println(HORIZONTAL_LINE);
    }

    private void listTasks() {
        System.out.println(HORIZONTAL_LINE);

        if (tasks.isEmpty()) {
            System.out.println("> No tasks added");
        } else {
            System.out.println("> These are your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.format("\t%d. %s", i + 1, tasks.get(i)));
            }
        }

        System.out.println(HORIZONTAL_LINE);
    }

    private void markTask(String line) {
        System.out.println(HORIZONTAL_LINE);

        // TODO: error handling
        String[] tokens = line.split(" ", 2);
        int taskNumber = Integer.parseInt(tokens[1]);
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println(String.format("> error: task %s not found", taskNumber));
            return;
        }
        int index = taskNumber - 1;
        tasks.get(index).setCompletion(true);
             
        System.out.println("> Nice! I've marked this task as done:");
        System.out.println(tasks.get(index));
        System.out.println(HORIZONTAL_LINE);
    }

    private void unmarkTask(String line) {
        System.out.println(HORIZONTAL_LINE);

        // TODO: error handling
        String[] tokens = line.split(" ", 2);
        int taskNumber = Integer.parseInt(tokens[1]);
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println(String.format("> error: task %s not found", taskNumber));
            return;
        }
        int index = taskNumber - 1;
        tasks.get(index).setCompletion(false);
             
        System.out.println("> OK, I've marked this task as not done yet:");
        System.out.println(String.format("\t%s", tasks.get(index)));
        System.out.println(HORIZONTAL_LINE);
    }

    private void listen() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.equals("bye")) {
                break;
            }

            if (line.equals("list")) {
                listTasks();
                continue;
            }

            if (line.startsWith("mark")) {
                markTask(line);
                continue;
            }

            if (line.startsWith("unmark")) {
                unmarkTask(line);
                continue;
            }

            if (line.startsWith("todo")) {
                addTask(new ToDo(line));
            }

            if (line.startsWith("deadline")) {
                addTask(new Deadline(line));
            }

            if (line.startsWith("event")) {
                addTask(new Event(line));
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
