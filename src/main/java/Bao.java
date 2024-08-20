import java.util.Scanner;
import java.util.ArrayList;
public class Bao {
    private static ArrayList<Task> taskList = new ArrayList<>();

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
        System.out.println("Bao says hello! Bao's name is Bao but you can call me Bao");
        System.out.println("Bao is ready for instructions");
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
                } else if (input.startsWith("mark")) {
                    String number = input.substring(5).trim();
                    try {
                        int index = Integer.parseInt(number) - 1;
                        checkIndex(index);
                        taskList.get(index).mark();
                        System.out.println("Bao has marked it as done!");
                        System.out.println(taskList.get(index));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Bao needs a task number to mark!");
                    }
                } else if (input.startsWith("unmark")) {
                    String number = input.substring(7).trim();
                    try {
                        int index = Integer.parseInt(number) - 1;
                        checkIndex(index);
                        taskList.get(index).unmark();
                        System.out.println("Bao has marked it as not done!");
                        System.out.println(taskList.get(index));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Bao needs a task number to unmark!");
                    }
                } else if (input.startsWith("todo")) {
                    String task = input.substring(5).trim();
                    if (task.isEmpty()) {
                        throw new IllegalArgumentException("Bao needs a description of the task!");
                    }
                    addTask(task, "T");
                    System.out.println("You have " + taskList.size() + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("deadline")) {
                    String task = input.substring(9).trim();
                    if (task.isEmpty()) {
                        throw new IllegalArgumentException("Bao needs a description of the task!");
                    }
                    addTask(task, "D");
                    System.out.println("You have " + taskList.size() + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("event")) {
                    String task = input.substring(6).trim();
                    if (task.isEmpty()) {
                        throw new IllegalArgumentException("Bao needs a description of the task!");
                    }
                    addTask(task, "E");
                    System.out.println("You have " + taskList.size() + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("delete")) {
                    if (taskList.isEmpty()) {
                        throw new IllegalArgumentException("Bao's list is empty");
                    }
                    String number = input.substring(7).trim();
                    try {
                        int index = Integer.parseInt(number) - 1;
                        checkIndex(index);
                        deleteTask(index);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Bao needs a task number to delete!");
                    }
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
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException("Bao needs you to refer to tasks within the range!");
        }
    }

    private static void deleteTask(int index) {
        Task removed = taskList.remove(index);
        System.out.println("Bao has removed this task:");
        System.out.println(removed.toString());
        System.out.println("Bao is now tracking " + taskList.size() + " tasks");
    }

    private static void showTasks() {
        if (taskList.isEmpty()) {
            System.out.println("Bao is tracking nothing!");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    private static void addTask(String taskDescription, String type) {
        if (taskList.size() < 100) {
            switch (type) {
                case "T" -> {
                    taskList.add(new ToDo(taskDescription));
                    System.out.println("Bao got it! Bao is now tracking:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                }
                case "D" -> {
                    int byIndex = taskDescription.indexOf("/by ");
                    if (byIndex == -1) {
                        throw new IllegalArgumentException("Bao needs the deadline to be after /by");
                    }
                    String deadline = taskDescription.substring(byIndex + 4);
                    String description = taskDescription.substring(0, byIndex - 1);
                    taskList.add(new Deadline(description, deadline));
                    System.out.println("Bao got it! Bao is now tracking:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
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
                    taskList.add(new Event(description, from, to));
                    System.out.println("Bao got it! Bao is now tracking:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                }
            }
        } else {
            System.out.println(baoSad);
            System.out.println("Bao cannot remember so many things :(");
        }
    }
}
