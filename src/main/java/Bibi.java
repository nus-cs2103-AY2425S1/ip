import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

        // Init
        initializeDataDirectory();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            restoreTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Hello from\n" + logo + "\n"
                    + "How can I help you?");
        printHorizontalLine();
        String cmd;
        int index;

        while (scanner.hasNext()) {
            // Preconfigured commands
            switch (cmd = scanner.next()) {
            case "bye":
                // Exit
                printHorizontalLine();
                System.out.println("See you soon :3");
                printHorizontalLine();
                return;
            case "list":
                printHorizontalLine();
                printTaskList(tasks);
                printHorizontalLine();
                break;
            case "mark":
                printHorizontalLine();
                if (!(cmd = scanner.nextLine().trim()).matches("\\d+")) {
                    System.out.println("Invalid command syntax: Please use \"mark <int>\"");
                } else if ((index = Integer.parseInt(cmd)) - 1 >= tasks.size() || index <= 0) {
                    System.out.println("Invalid task index");
                } else {
                    System.out.printf("Alrighty, marked the following task as done:%n");
                    Task t = tasks.get(index - 1);
                    t.markAsDone();
                    System.out.println(t);
                }
                printHorizontalLine();
                break;
            case "unmark":
                printHorizontalLine();
                if (!(cmd = scanner.nextLine().trim()).matches("\\d+")) {
                    System.out.println("Invalid command syntax: Please use \"unmark <int>\"");
                } else if ((index = Integer.parseInt(cmd)) - 1 >= tasks.size() || index <= 0) {
                    System.out.println("Invalid task index");
                } else {
                    System.out.printf("Oops, we'll get 'em next time:%n");
                    Task t = tasks.get(index - 1);
                    t.markAsNotDone();
                    System.out.println(t);
                }
                printHorizontalLine();
                break;
            case "todo":
                printHorizontalLine();
                if (!(cmd = scanner.nextLine()).matches(".+")) {
                    System.out.println("Invalid todo syntax: Please use \"todo <description>\"");
                } else {
                    ToDo td = new ToDo(cmd.stripIndent());
                    addToTaskList(tasks, td);

                    // Console
                    System.out.printf("added: \"%s\" to task list%n", td);
                    System.out.printf("You now have %d task(s) to do%n", tasks.size());
                }
                printHorizontalLine();
                break;
            case "deadline":
                printHorizontalLine();
                if (!(cmd = scanner.nextLine()).matches(".+ /by .+")) {
                    System.out.println("Invalid deadline syntax: Please use \"deadline <description> /by <deadline>\"");
                } else {
                    String[] input = cmd.split(" /by ");
                    Deadline dl = new Deadline(input[0].stripIndent(), input[1]);
                    addToTaskList(tasks, dl);

                    // Console
                    System.out.printf("added: \"%s\" to task list%n", dl);
                    System.out.printf("You now have %d task(s) to do%n", tasks.size());
                }
                printHorizontalLine();
                break;
            case "event":
                printHorizontalLine();
                if (!(cmd = scanner.nextLine()).matches(".+ /from .+ /to .+")) {
                    System.out.println("Invalid event syntax: Please use \"event <description> /from <time> /to <time>\"");
                } else {
                    String[] input = cmd.split(" /from ");
                    String[] interval = input[1].split(" /to ");
                    Event e = new Event(input[0].stripIndent(), interval[0], interval[1]);
                    tasks.add(e);

                    // Console
                    System.out.printf("added: \"%s\" to task list%n", e);
                    System.out.printf("You now have %d task(s) to do%n", tasks.size());
                }
                printHorizontalLine();
                break;
            case "remove":
                printHorizontalLine();
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid event syntax: Please use \"remove <index>\"");
                } else if ((index = scanner.nextInt()) > tasks.size() || index <= 0) {
                    System.out.println("Invalid task index");
                } else {
                    System.out.println("You will never see this task ever again >:(");
                    System.out.printf("Removed %s from task list%n", removeFromTaskList(tasks, index).toString());
                    System.out.printf("You now have %d task(s) to do%n", tasks.size());
                }
                printHorizontalLine();
                break;
            default:
                // Console
                printHorizontalLine();
                System.out.printf("%s is an unknown command%n", cmd);
                printHorizontalLine();
            }
        }
    }

    private static void restoreTasks(ArrayList<Task> tasks) throws FileNotFoundException {
        File file = new File("data/list.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("Save file not found");
        }
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String taskLine = s.nextLine();
            switch (taskLine.charAt(1)) {
            case 'T':

            case 'D':
            case 'E':
            default:
            }
        }
    }

    private static void initializeDataDirectory() {
        String dirPath = "data";
        String filePathStr = "data/list.txt";
        if (!Files.exists(Path.of(dirPath))) {
            new File(dirPath).mkdirs();
        }

        // Check if previous list exists
        Path filePath = Path.of(filePathStr);
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println("Something went wrong during file creation");
            }
        }
    }

    private static Task removeFromTaskList(ArrayList<Task> tasks, int index) {
        return tasks.remove(index - 1);
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
        System.out.println("------------------------------------------------------------");
    }
}
