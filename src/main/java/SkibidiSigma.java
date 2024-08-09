import java.util.Scanner;

public class SkibidiSigma {
    public static void main(String[] args) {
        String logo = """
                 _____ _    _ _     _     _ _   _____ _                      \s
                /  ___| |  (_) |   (_)   | (_) /  ___(_)                     \s
                \\ `--.| | ___| |__  _  __| |_  \\ `--. _  __ _ _ __ ___   __ _\s
                 `--. \\ |/ / | '_ \\| |/ _` | |  `--. \\ |/ _` | '_ ` _ \\ / _` |
                /\\__/ /   <| | |_) | | (_| | | /\\__/ / | (_| | | | | | | (_| |
                \\____/|_|\\_\\_|_.__/|_|\\__,_|_| \\____/|_|\\__, |_| |_| |_|\\__,_|
                                                         __/ |               \s
                                                        |___/                \s
                """;

        String horizontalLine = "____________________________________________________________";

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        System.out.println(horizontalLine + "\nHello! I'm SkibidiSigma\n" + logo + "\nWhat can I do for you?\n" + horizontalLine);

        while (true) {
            String userInput = scanner.nextLine().trim();

            if ("bye".equalsIgnoreCase(userInput)) {
                System.out.println(
                        horizontalLine +
                        "\nCatch ya on the flip side, my dude! \uD83D\uDE80\uD83E\uDD2F See ya soon!\n" +
                        horizontalLine);
                break;
            }

            if ("list".equalsIgnoreCase(userInput)) {
                System.out.println(horizontalLine);
                taskList.listTasks();
                System.out.println(horizontalLine);
            } else if (userInput.toLowerCase().startsWith("mark")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    System.out.println(horizontalLine);
                    taskList.markTaskAsDone(taskNumber);
                    System.out.println(horizontalLine);
                } catch (Exception e) {
                    System.out.println("Invalid command syntax. Usage: mark <task_number>");
                    System.out.println(horizontalLine);
                }
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    System.out.println(horizontalLine);
                    taskList.markTaskAsNotDone(taskNumber);
                    System.out.println(horizontalLine);
                } catch (Exception e) {
                    System.out.println("Invalid command syntax. Usage: unmark <task_number>");
                    System.out.println(horizontalLine);
                }
            } else {
                System.out.println(horizontalLine);
                taskList.addTask(userInput);
                System.out.println(horizontalLine);
            }
        }

        scanner.close();
    }
}
