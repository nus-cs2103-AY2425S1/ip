package orion.ui;

import orion.task.Task;
import java.util.List;
import java.util.Scanner;

public class UI {
    private static final String HORIZONTAL_LINE = "────────────────────────────────────────";
    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        executeWithFormatting(() -> {
            System.out.println("Hello! I'm Orion");
            System.out.println("What can I do for you?");
        });
    }

    public void showGoodbye() {
        executeWithFormatting(() -> {
            System.out.println("Bye. Hope to see you again soon!");
        });
    }

    public void showError(String message) {
        executeWithFormatting(() -> {
            System.out.println("Error: " + message);
        });
    }

    public void showTaskAdded(Task task, int totalTasks) {
        executeWithFormatting(() -> {
            System.out.println("     Got it. I've added this task:\n" + task + "\nNow you have " + totalTasks
                    + " tasks in the list");
        });
    }

    public void showTaskList(List<Task> tasks) {
        executeWithFormatting(() -> {
            if (tasks.isEmpty()) {
                System.out.println("There are no tasks.");
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i).toString());
                }
            }
        });
    }

    public void showTaskList(List<Task> tasks, boolean find) {
        if (!find) {
            showTaskList(tasks);
        } else {
            executeWithFormatting(() -> {
                if (tasks.isEmpty()) {
                    System.out.println("No matching tasks found.");
                } else {
                    System.out.println("Here are the matching tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                }
            });
        }
    }

    public void showTaskMarked(Task task) {
        executeWithFormatting(() -> {
            System.out.println("Marked task as done: " + task);
        });
    }

    public void showTaskUnmarked(Task task) {
        executeWithFormatting(() -> {
            System.out.println("Unmarked task: " + task);
        });
    }

    public void showTaskDeleted(Task task, int remainingTasks) {
        executeWithFormatting(() -> {
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + remainingTasks + " tasks in the list.");
        });
    }

    public String readUserInput() {
        return scanner.nextLine();
    }

    private void executeWithFormatting(Runnable action) {
        System.out.println(HORIZONTAL_LINE);
        action.run();
        System.out.println(HORIZONTAL_LINE);
    }
}
