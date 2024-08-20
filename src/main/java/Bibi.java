import java.util.Scanner;

public class Bibi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = """
                        ########   #######   ########   #######\s
                        #       #     #      #       #     #   \s
                        ########      #      ########      #   \s
                        #       #     #      #       #     #   \s
                        #       #     #      #       #     #   \s
                        ########   #######   ########   #######\s
                      """;

        // Mini Database
        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println("Hello from\n" + logo + "\n"
                    + "How can I help you?");
        printHorizontalLine();

        String cmd;
        while (scanner.hasNext()) {
            cmd = scanner.nextLine();

            // Preconfigured commands
            if (cmd.equals("bye")) {
                break;
            } else if (cmd.equals("list")) {
                printHorizontalLine();
                printTaskList(tasks, taskCount);
                printHorizontalLine();
            } else {
                tasks[taskCount++] = cmd;
                printHorizontalLine();
                System.out.printf("added: \"%s\" to task list%n", cmd);
                printHorizontalLine();
            }
        }

        // Exit
        printHorizontalLine();
        System.out.println("See you soon :3");
        printHorizontalLine();
    }

    private static void printTaskList(String[] tasks, int taskCount) {
        for (int i = 1; i <= taskCount; i++) {
            System.out.printf("%d: %s%n", i, tasks[i - 1]);
        }
    }

    private static void printHorizontalLine() {
        System.out.println("--------------------------------");
    }
}
