import task.Task;

import java.util.List;

public class PrintFormatter {
    static void print(String... args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    static void printAdded(Task task) {
        System.out.println("Added: " + task);
    }

    static void printCompleted(Task task) {
        System.out.println("Completed: " + task);
    }

    static void printDeleted(Task task) {
        System.out.println("Deleted: " + task);
    }

    static void printTaskList(List<Task> taskList) {
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.print("\t".repeat(1));
            System.out.println(i + ". " + taskList.get(i));
        }
    }

    static void printException(String args) {
        System.out.println("Error found: " + args);
    }
}
