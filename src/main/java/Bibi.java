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
        Task[] tasks = new Task[100];
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
            } else if (cmd.split(" ")[0].equals("mark")) {
                String[] cmdArr = cmd.split(" ");
                printHorizontalLine();
                if (cmdArr.length != 2) {
                    System.out.println("Invalid command syntax: Please use \"mark <int>\"");
                } else if (Integer.parseInt(cmdArr[1]) <= 0 || Integer.parseInt(cmdArr[1]) - 1 >= taskCount) {
                    System.out.println("Invalid task index");
                } else {
                    System.out.printf("Alrighty, marked the following task as done:%n");
                    Task t = tasks[Integer.parseInt(cmdArr[1]) - 1];
                    t.markAsDone();
                    System.out.println(t);
                    printHorizontalLine();
                }
            } else if (cmd.split(" ")[0].equals("unmark")) {
                String[] cmdArr = cmd.split(" ");
                printHorizontalLine();
                if (cmdArr.length != 2) {
                    System.out.println("Invalid command syntax: Please use \"mark <int>\"");
                } else if (Integer.parseInt(cmdArr[1]) <= 0 || Integer.parseInt(cmdArr[1]) - 1 >= taskCount) {
                    System.out.println("Invalid task index");
                } else {
                    System.out.printf("Oops, we'll get 'em next time:%n");
                    Task t = tasks[Integer.parseInt(cmdArr[1]) - 1];
                    t.markAsNotDone();
                    System.out.println(t);
                    printHorizontalLine();
                }
            } else { // Add to task list
                switch (cmd.split(" ")[0]) {
                    case "todo": {
                        printHorizontalLine();
                        if (cmd.length() <= 5) {
                            System.out.println("Invalid todo syntax: Please use \"todo <description>\"");
                        } else {
                            ToDo td = new ToDo(cmd.substring(5));
                            tasks[taskCount++] = td;

                            // Console
                            System.out.printf("added: \"%s\" to task list%n", td);
                        }
                        break;
                    }
                    case "deadline": {
                        printHorizontalLine();
                        if (cmd.length() <= 9) {
                            System.out.println("Invalid deadline syntax: Please use \"deadline <description> /by <deadline>\"");
                        } else {
                            String[] input = cmd.substring(9).split(" /by ");
                            Deadline dl = new Deadline(input[0], input[1]);
                            tasks[taskCount++] = dl;

                            // Console
                            System.out.printf("added: \"%s\" to task list%n", dl);
                        }
                        break;
                    }
                    case "event": {
                        printHorizontalLine();
                        if (cmd.length() <= 6) {
                            System.out.println("Invalid event syntax: Please use \"event <description> /from <time> /to <time>\"");
                        } else {
                            String[] input = cmd.substring(6).split(" /from ");
                            String[] interval = input[1].split(" /to ");
                            Event e = new Event(input[0], interval[0], interval[1]);
                            tasks[taskCount++] = e;

                            // Console
                            System.out.printf("added: \"%s\" to task list%n", e);
                        }
                        break;
                    }
                    default: {
                        // Console
                        printHorizontalLine();
                        System.out.printf("%s is an unknown command%n", cmd);
                    }
                }
                System.out.printf("You now have %d task(s) to do%n", taskCount);
                printHorizontalLine();
            }
        }

        // Exit
        printHorizontalLine();
        System.out.println("See you soon :3");
        printHorizontalLine();
    }

    private static void printTaskList(Task[] tasks, int taskCount) {
        if (taskCount == 0) {
            System.out.println("Good for you, nothing to do today :3");
            return;
        }

        for (int i = 1; i <= taskCount; i++) {
            System.out.printf("%d: %s%n", i, tasks[i - 1]);
        }
    }

    private static void printHorizontalLine() {
        System.out.println("--------------------------------");
    }
}
