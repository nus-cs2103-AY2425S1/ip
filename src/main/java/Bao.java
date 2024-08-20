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
            try {
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
                    String number = input.substring(5).trim();
                    try {
                        int index = Integer.parseInt(number) - 1;
                        checkIndex(index);
                        taskList[index].mark();
                        System.out.println("Bao has marked it as done!");
                        System.out.println(taskList[index]);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Bao needs a task number to mark!");
                    }
                } else if (input.startsWith("unmark ")) {
                    String number = input.substring(7).trim();
                    try {
                        int index = Integer.parseInt(number) - 1;
                        checkIndex(index);
                        taskList[index].unmark();
                        System.out.println("Bao has marked it as not done!");
                        System.out.println(taskList[index]);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Bao needs a task number to unmark!");
                    }
                } else if (input.startsWith("todo ")) {
                    String task = input.substring(5).trim();
                    if (task.isEmpty()) {
                        throw new IllegalArgumentException("Bao needs a description of the task!");
                    }
                    addTask(task, "T");
                    System.out.println("You have " + taskCount + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("deadline ")) {
                    String task = input.substring(9).trim();
                    if (task.isEmpty()) {
                        throw new IllegalArgumentException("Bao needs a description of the task!");
                    }
                    addTask(task, "D");
                    System.out.println("You have " + taskCount + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("event ")) {
                    String task = input.substring(6).trim();
                    if (task.isEmpty()) {
                        throw new IllegalArgumentException("Bao needs a description of the task!");
                    }
                    addTask(task, "E");
                    System.out.println("You have " + taskCount + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new UnsupportedOperationException("Bao needs a proper command :(");
                }
            } catch (Exception e){
                System.out.println(baoSad);
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void checkIndex(int index) {
        if (index < 0 || index >= taskCount) {
            throw new IndexOutOfBoundsException("Bao needs you to refer to tasks within the range!");
        }
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
                    if (byIndex == -1) {
                        throw new IllegalArgumentException("Bao needs the deadline to be after /by");
                    }
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
                    if (fromIndex == -1 || toIndex == -1) {
                        throw new IllegalArgumentException("Bao needs the start and end to be after /from and /to");
                    }
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
