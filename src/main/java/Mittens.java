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
        System.out.println("\nHere are the tasks in your list :3");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d. %s\n", i + 1, task.toString());
        }
        System.out.print("\n");
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
            } else {
                Task newTask = new Task(input);
                addTask(newTask);
            }
        }

        exit();
    }
}
