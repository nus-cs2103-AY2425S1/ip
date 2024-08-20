import java.util.Scanner;
public class Bao {
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    private static String baoHappy =   "     ___   \n"
            + "   /     \\  \n"
            + "  /       \\ \n"
            + " |  ^   ^  |\n"
            + " |    3    |\n"
            + " \\________/ \n";

    private static String baoSad =   "     ___   \n"
            + "   /     \\  \n"
            + "  /       \\ \n"
            + " |  T   T  |\n"
            + " |    ^    |\n"
            + " \\________/ \n";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(baoHappy);
        System.out.println("Hello! I'm Bao but you can call me Bao");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(baoSad);
                System.out.println("Bye :( Come back soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                showTasks();
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                taskList[index].mark();
                System.out.println("Bao has marked it as done!");
                System.out.println(taskList[index]);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                taskList[index].unmark();
                System.out.println("Bao has marked it as not done!");
                System.out.println(taskList[index]);
            } else if (input.startsWith("todo ")) {
                String task = input.substring(5);
                addTask(task, "T");
                System.out.println("You have " + taskCount + " tasks in the list");
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }

    private static void showTasks() {
        if (taskCount == 0) {
            System.out.println("You haven't told Bao anything yet!");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + taskList[i]);
            }
        }
    }

    private static void addTask(String message, String type) {
        if (taskCount < 100) {
            if (type == "T") {
                taskList[taskCount] = new ToDo(message, null);
                System.out.println("Bao got it! Bao is now tracking:");
                System.out.println(taskList[taskCount].toString());
                taskCount++;
            }
        } else {
            System.out.println(baoSad);
            System.out.println("Bao cannot remember so many things :(");
        }
    }
}
