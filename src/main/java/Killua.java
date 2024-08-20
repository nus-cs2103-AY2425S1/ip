import java.util.ArrayList;
import java.util.Scanner;

public class Killua {
    private static final String LINE = "____________________________________________________________";

    private static void printLine() {
        System.out.println(LINE);
    }

    private static void add(String message){
        printLine();
        System.out.println("added task: " + message);
        printLine();
    }

    private static void bye(){
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void list(ArrayList<Task> tasks){
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s%n", i + 1, task);
        }
        printLine();
    }

    private static void markTaskDone(ArrayList<Task> tasks, int taskNumber) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        tasks.get(taskNumber).markAsDone();
        System.out.println("  " + tasks.get(taskNumber));
        printLine();
    }

    private static void unmarkTask(ArrayList<Task> tasks, int taskNumber) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        tasks.get(taskNumber).unmark();
        System.out.println("  " + tasks.get(taskNumber));
        printLine();
    }

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Killua");
        System.out.println("What can I do for you?");
        printLine();

        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (flag) {
            String command = scanner.nextLine().trim();

            switch(command.toLowerCase()) {
                case "bye" -> {
                    bye();
                    flag = false;
                }
                case "list" -> list(tasks);
                default -> {
                    if (command.startsWith("mark ")) {
                        int taskNumber = Integer.parseInt(command.substring(5).trim());
                        markTaskDone(tasks, taskNumber-1);
                    } else if (command.startsWith("unmark ")) {
                        int taskNumber = Integer.parseInt(command.substring(7).trim());
                        unmarkTask(tasks, taskNumber-1);
                    } else {
                        tasks.add(new Task(command));
                        add(command);
                    }
                }
            }
        }

        scanner.close();
    }
}
