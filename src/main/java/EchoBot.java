import java.util.ArrayList;
import java.util.Scanner;

public class EchoBot {
    private static final String BYE = "bye";
    private static String Logo = " _ _ _  _ _ _         _ _ _\n"
            + "|      |      |     ||     | \n"
            + "|_ _ _ |      |_ _ _||     |\n"
            + "|      |      |     ||     |\n"
            + "|_ _ _ |_ _ _ |     ||_ _ _|\n";
    private static ArrayList<Task> allTasks = new ArrayList<>();

    public static void dashline() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        System.out.println("Hello from\n" + Logo);
        dashline();
        System.out.println("Hello! I'm EchoBot");
        System.out.println("What can I do for you?");
        dashline();
    }

    public static void listAllTask() {
        dashline();
        String tasks = "";
        for (int i = 0; i < allTasks.size(); i++) {
            tasks += (i + 1) + ". " + allTasks.get(i).toString();
            tasks += (i == allTasks.size() - 1) ? "" : "\n";
        }
        System.out.println(tasks);
        dashline();
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EchoBot.greet();
        while (true) {
            String userInput = scanner.nextLine();
            String[] cmdParts = userInput.split(" ");
            String CMD = cmdParts[0];
            try {
                switch (CMD) {
                    case "list":
                        EchoBot.listAllTask();
                        break;
                    case "mark":
                        int markIdx = Integer.parseInt(cmdParts[1]) - 1;
                        Task markTask = allTasks.get(markIdx);
                        markTask.mark();
                        break;
                    case "unmark":
                        int unmarkIdx = Integer.parseInt(cmdParts[1]) - 1;
                        Task unmarkTask = allTasks.get(unmarkIdx);
                        unmarkTask.unmark();
                        break;
                    default:
                        Task task = new Task(userInput);
                        allTasks.add(task);
                }
            } catch (IndexOutOfBoundsException e) {
                String msg = "Input Error: " + e.toString();
                System.out.println(msg);
                System.exit(0);
            }
            if (CMD.equals(EchoBot.BYE)) {
                EchoBot.bye();
                break;
            }
        }
    }
}
