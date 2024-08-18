import java.util.ArrayList;
import java.util.Scanner;

public class EchoBot {
    private static final String LIST = "list";
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
        dashline();
        System.out.println("Bye. Hope to see you again soon!");
        dashline();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EchoBot.greet();
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals(EchoBot.LIST)) {
                EchoBot.listAllTask();
                continue;
            }
            if (userInput.equals(EchoBot.BYE)) {
                EchoBot.bye();
                break;
            }
            Task task = new Task(userInput);
            allTasks.add(task);
        }
    }
}
