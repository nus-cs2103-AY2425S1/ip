import java.util.ArrayList;
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
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Hello from\n" + logo + "\n"
                    + "How can I help you?");
        printHorizontalLine();

        String cmd;
        while (scanner.hasNext()) {
            cmd = scanner.nextLine();

            // Preconfigured commands
            switch (cmd.split(" ")[0]) {
                case "bye": {
                    break;
                }
                case "list": {
                    printHorizontalLine();
                    printTaskList(tasks);
                    printHorizontalLine();
                    break;
                }
                case "mark": {
                    String[] cmdArr = cmd.split(" ");
                    printHorizontalLine();
                    if (cmdArr.length != 2) {
                        System.out.println("Invalid command syntax: Please use \"mark <int>\"");
                    } else if (Integer.parseInt(cmdArr[1]) <= 0 || Integer.parseInt(cmdArr[1]) - 1 >= tasks.size()) {
                        System.out.println("Invalid task index");
                    } else {
                        System.out.printf("Alrighty, marked the following task as done:%n");
                        Task t = tasks.get(Integer.parseInt(cmdArr[1]) - 1);
                        t.markAsDone();
                        System.out.println(t);
                        printHorizontalLine();
                    }
                    break;
                }
                case "unmark": {
                    String[] cmdArr = cmd.split(" ");
                    printHorizontalLine();
                    if (cmdArr.length != 2) {
                        System.out.println("Invalid command syntax: Please use \"mark <int>\"");
                    } else if (Integer.parseInt(cmdArr[1]) <= 0 || Integer.parseInt(cmdArr[1]) - 1 >= tasks.size()) {
                        System.out.println("Invalid task index");
                    } else {
                        System.out.printf("Oops, we'll get 'em next time:%n");
                        Task t = tasks.get(Integer.parseInt(cmdArr[1]) - 1);
                        t.markAsNotDone();
                        System.out.println(t);
                        printHorizontalLine();
                    }
                    break;
                }
                case "todo": {
                    printHorizontalLine();
                    if (!cmd.matches("todo .+")) {
                        System.out.println("Invalid todo syntax: Please use \"todo <description>\"");
                    } else {
                        ToDo td = new ToDo(cmd.substring(5));
                        addToTaskList(tasks, td);

                        // Console
                        System.out.printf("added: \"%s\" to task list%n", td);
                        System.out.printf("You now have %d task(s) to do%n", tasks.size());
                    }
                    break;
                }
                case "deadline": {
                    printHorizontalLine();
                    if (!cmd.matches("deadline .+ /by .+")) {
                        System.out.println("Invalid deadline syntax: Please use \"deadline <description> /by <deadline>\"");
                    } else {
                        String[] input = cmd.substring(9).split(" /by ");
                        Deadline dl = new Deadline(input[0], input[1]);
                        addToTaskList(tasks, dl);

                        // Console
                        System.out.printf("added: \"%s\" to task list%n", dl);
                        System.out.printf("You now have %d task(s) to do%n", tasks.size());
                    }
                    break;
                }
                case "event": {
                    printHorizontalLine();
                    if (!cmd.matches("event .+ /from .+ /to .+")) {
                        System.out.println("Invalid event syntax: Please use \"event <description> /from <time> /to <time>\"");
                    } else {
                        String[] input = cmd.substring(6).split(" /from ");
                        String[] interval = input[1].split(" /to ");
                        Event e = new Event(input[0], interval[0], interval[1]);
                        tasks.add(e);

                        // Console
                        System.out.printf("added: \"%s\" to task list%n", e);
                        System.out.printf("You now have %d task(s) to do%n", tasks.size());
                    }
                    break;
                }
                default: {
                    // Console
                    printHorizontalLine();
                    System.out.printf("%s is an unknown command%n", cmd);
                }
            }
        }

        // Exit
        printHorizontalLine();
        System.out.println("See you soon :3");
        printHorizontalLine();
        }

    private static void addToTaskList(ArrayList<Task> tasks, Task t) {
        tasks.add(t);
    }

    private static void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Good for you, nothing to do today :3");
            return;
        }

        for (int i = 1; i <= tasks.size(); i++) {
            System.out.printf("%d: %s%n", i, tasks.get(i - 1));
        }
    }

    private static void printHorizontalLine() {
        System.out.println("--------------------------------");
    }
}
