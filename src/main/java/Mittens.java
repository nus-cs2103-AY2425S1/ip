import java.util.ArrayList;
import java.util.Scanner;

public class Mittens {
    private final static String GREETING_MESSAGE = """
            
                ██          ██                ____________________
              ██░░██      ██░░██             / Hi, I'm Mittens!   \\
              ██░░░░██████░░░░██             | I'm a cat! Meow :3 |
            ██░░░░░░░░░░░░░░░░░░██           \\ How can I help?    /
            ██░░░░░░░░░░░░░░░░░░██            --------------------
            ██░░██░░░░░░░░██░░░░██
            ██░░██░░░░░░░░██░░░░██    ████
            ██░░░░░░░░░░░░░░░░░░██  ██    ██
              ████▒▒▒▒▒▒▒▒▒▒████    ██    ██
                  ██░░▒▒▒▒░░░░░░██    ██    ██
                  ██░░██░░████░░░░██  ██░░░░██
                  ██░░████░░░░██░░██  ██░░░░██
                  ██  ████░░░░░░░░████▒▒▒▒▒▒██
                  ██  ██    ░░░░░░██▒▒▒▒████
                  ██████████████████████
            """;

    private final static String EXIT_MESSAGE = """
            
                ██          ██                _____________
              ██░░██      ██░░██             ( Bye-bye! :3 )
              ██░░░░██████░░░░██              -------------
            ██░░░░░░░░░░░░░░░░░░██
            ██░░░░░░░░░░░░░░░░░░██
            ██░░██░░░░░░░░██░░░░██
            ██░░██░░░░░░░░██░░░░██    ████
            ██░░░░░░░░░░░░░░░░░░██  ██    ██
              ████▒▒▒▒▒▒▒▒▒▒████    ██    ██
                  ██░░▒▒▒▒░░░░░░██    ██    ██
                  ██░░██░░████░░░░██  ██░░░░██
                  ██░░████░░░░██░░██  ██░░░░██
                  ██  ████░░░░░░░░████▒▒▒▒▒▒██
                  ██  ██    ░░░░░░██▒▒▒▒████
                  ██████████████████████
            """;

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void greet() {
        System.out.println(GREETING_MESSAGE);
    }

    public static void echo(String command) {
        int len = command.length();
        String message = """
                
                    ██          ██                %s
                  ██░░██      ██░░██             ( %s )
                  ██░░░░██████░░░░██              %s
                ██░░░░░░░░░░░░░░░░░░██
                ██░░░░░░░░░░░░░░░░░░██
                ██░░██░░░░░░░░██░░░░██
                ██░░██░░░░░░░░██░░░░██    ████
                ██░░░░░░░░░░░░░░░░░░██  ██    ██
                  ████▒▒▒▒▒▒▒▒▒▒████    ██    ██
                      ██░░▒▒▒▒░░░░░░██    ██    ██
                      ██░░██░░████░░░░██  ██░░░░██
                      ██░░████░░░░██░░██  ██░░░░██
                      ██  ████░░░░░░░░████▒▒▒▒▒▒██
                      ██  ██    ░░░░░░██▒▒▒▒████
                      ██████████████████████
                """
                .formatted("_".repeat(len + 2),
                        command, "-".repeat(len + 2));

        System.out.println(message);
    }

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.printf("\nI've added \"%s\" to your list :3\n\n", task.getDescription());
    }

    public static void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("\nMeow?! Your list is empty!\n");
            return;
        }
        System.out.printf("\nYou have %d tasks in your list, here they are :3\n", tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d. %s\n", i + 1, task.toString());
        }
        System.out.print("\n");
    }

    public static void markTaskAsDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        System.out.printf("\nMeow, I scratched the check box for you:\n%s\n\n", task.toString());
    }

    public static void markTaskAsNotDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsNotDone();
        System.out.printf("\nMeow, I unscratched the check box for you:\n%s\n\n", task.toString());
    }

    public static void exit() {
        System.out.println(EXIT_MESSAGE);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        greet();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                markTaskAsDone(index);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                markTaskAsNotDone(index);
            } else if (input.startsWith("todo")) {
                String description = input.substring(5);

                Todo newTodo = new Todo(description);
                addTask(newTodo);
            } else if (input.startsWith("deadline")) {
                // Separate the inputs so that the first element contains the description while
                // the rest contains flags.
                String[] inputs = input.split(" /");
                String description = inputs[0].substring(9);
                // Assuming order of flags are: by
                String by = inputs[1].substring(3);

                Deadline newDeadline = new Deadline(description, by);
                addTask(newDeadline);
            } else if (input.startsWith("event")) {
                // Separate the inputs so that the first element contains the description while
                // the rest contains flags.
                String[] inputs = input.split(" /");
                String description = inputs[0].substring(6);
                // Assuming order of flags are: from, to
                String from = inputs[1].substring(5);
                String to = inputs[2].substring(3);

                Event newEvent = new Event(description, from, to);
                addTask(newEvent);
            }
        }

        exit();
    }
}
