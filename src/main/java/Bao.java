import java.util.Scanner;
public class Bao {
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    private static String baoHappy =
              "     ___\n"
            + "   /     \\\n"
            + "  /       \\\n"
            + " |  ^   ^  |\n"
            + " |    3    |\n"
            + " \\________/\n";

    private static String baoSad =
              "     ___\n"
            + "   /     \\\n"
            + "  /       \\\n"
            + " |  T   T  |\n"
            + " |    ^    |\n"
            + " \\________/\n";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(baoHappy);
        System.out.println("Hello! I'm Bao but you can call me Bao");
        System.out.println("What can Bao do for you?");
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
            } else if (input.startsWith("deadline ")) {
                String task = input.substring(9);
                addTask(task, "D");
                System.out.println("You have " + taskCount + " tasks in the list");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("event ")) {
                String task = input.substring(6);
                addTask(task, "E");
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

    private static void addTask(String taskDescription, String type) {
        if (taskCount < 100) {
            switch (type) {
                case "T" -> {
                    taskList[taskCount] = new ToDo(taskDescription);
                    System.out.println("Bao got it! Bao is now tracking:");
                    System.out.println(taskList[taskCount].toString());
                    taskCount++;
                }
                case "D" -> {
                    int byIndex = taskDescription.indexOf("/by ");
                    String deadline = taskDescription.substring(byIndex + 4);
                    String description = taskDescription.substring(0, byIndex - 1);
                    taskList[taskCount] = new Deadline(description, deadline);
                    System.out.println("Bao got it! Bao is now tracking:");
                    System.out.println(taskList[taskCount].toString());
                    taskCount++;
                }
                case "E" -> {
                    int fromIndex = taskDescription.indexOf("/from ");
                    int toIndex = taskDescription.indexOf("/to ");
                    String from = taskDescription.substring(fromIndex + 6, toIndex - 1);
                    String to = taskDescription.substring(toIndex + 4);
                    String description = taskDescription.substring(0, fromIndex - 1);
                    taskList[taskCount] = new Event(description, from, to);
                    System.out.println("Bao got it! Bao is now tracking:");
                    System.out.println(taskList[taskCount].toString());
                    taskCount++;
                }
            }
        } else {
            System.out.println(baoSad);
            System.out.println("Bao cannot remember so many things :(");
        }
    }
}
