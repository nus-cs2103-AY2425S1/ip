import java.util.*;
import task.*;

public class Alice {
    private static final String NAME = "Alice";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private final List<Task> tasks;

    public Alice() {
        this.tasks = new ArrayList<>();
    }

    private void say(String message) {
        System.out.println(String.format("> %s", message));
    }

    private void greet() {
        System.out.println(HORIZONTAL_LINE);
        say(String.format("Hello! I'm %s.", NAME));
        say("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private void bye() {
        System.out.println(HORIZONTAL_LINE);
        say("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    private void echo(String line) {
        // echo user inputs
        System.out.println(HORIZONTAL_LINE);
        say(String.format("%s", line));
        System.out.println(HORIZONTAL_LINE);
    }

    private void warn(String message) {
        System.out.println(HORIZONTAL_LINE);
        say(String.format("Oops! %s", message));
        System.out.println(HORIZONTAL_LINE);
    }

    private void addTask(Task task) {
        tasks.add(task);
        System.out.println(HORIZONTAL_LINE);
        say("Got it. I've added this task:");
        System.out.println(String.format("\t%s", task));
        System.out.println(HORIZONTAL_LINE);
    }

    private void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        if (tasks.isEmpty()) {
            System.out.println("> You have no tasks.");
        } else {
            System.out.println("> These are your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.format("\t%d. %s", i + 1, tasks.get(i)));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private void markTask(String line) {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            warn("Invalid task number. Usage: mark <task number>");
            return;
        }

        int taskNumber = Integer.parseInt(tokens[1]);
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            warn("Invalid task number. Usage: mark <task number>");
            return;
        }

        int index = taskNumber - 1;
        tasks.get(index).setCompletion(true);
        System.out.println(HORIZONTAL_LINE);
        say("Nice! I've marked this task as done:");
        System.out.println(String.format("\t%s", tasks.get(index)));
        System.out.println(HORIZONTAL_LINE);
    }

    private void unmarkTask(String line) {
        System.out.println(HORIZONTAL_LINE);
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            warn("Invalid task number. Usage: unmark <task number>");
            return;
        }

        int taskNumber = Integer.parseInt(tokens[1]);
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            warn("Invalid task number. Usage: unmark <task number>");
            return;
        }

        int index = taskNumber - 1;
        tasks.get(index).setCompletion(false);             
        say("OK, I've marked this task as not done yet:");
        System.out.println(String.format("\t%s", tasks.get(index)));
        System.out.println(HORIZONTAL_LINE);
    }

    private void listen() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String input = line.trim().toLowerCase();
            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                listTasks();
                continue;
            }

            if (input.startsWith("mark")) {
                markTask(line);
                continue;
            }

            if (input.startsWith("unmark")) {
                unmarkTask(line);
                continue;
            }

            if (input.startsWith("todo")) {
                try {
                    Task toDo = new ToDo(line);
                    addTask(toDo);
                } catch (InvalidTaskException exception) {
                    warn(String.format("%s Usage: todo <description>", exception));
                }
                continue;
            }

            if (input.startsWith("deadline")) {
                try {
                    Task deadline = new Deadline(line);
                    addTask(deadline);
                } catch (InvalidTaskException exception) {
                    warn(String.format("%s Usage: deadline <description> /by <deadline>", exception));
                }
                continue;
            }

            if (input.startsWith("event")) {
                try {
                    Task event = new Event(line);
                    addTask(event);
                } catch (InvalidTaskException exception) {
                    warn(String.format("%s Usage: event <description> /from <start> /to <end>", exception));
                }
                continue;
            }

            // default
            echo(line);
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
