package ui;

import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void showWelcome() {
        System.out.println("___________________________________________________________");
        System.out.println(" Hello! I'm Friday");
        System.out.println(" What can I do for you?");
        System.out.println("___________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("___________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("___________________________________________________________");
    }

    public void showTaskList(List<Task> tasks) {
        System.out.println("    _______________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("    _______________________________________________________");
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("    _______________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
        System.out.println("    _______________________________________________________");
    }

    public void showTaskRemoved(Task task, int size) {
        System.out.println("    _______________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
        System.out.println("    _______________________________________________________");
    }

    public void showTaskMarked(Task task) {
        System.out.println("    _______________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        System.out.println("    _______________________________________________________");
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("    _______________________________________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        System.out.println("    _______________________________________________________");
    }

    public void showError(String message) {
        System.out.println("    _______________________________________________________");
        System.out.println("     " + "OOPS!!! " + message);
        System.out.println("    _______________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("    _______________________________________________________");
        System.out.println("     An error occurred while loading tasks.");
        System.out.println("    _______________________________________________________");
    }

    public void showSearchList(List<Task> tasks, LocalDate searchDate) {
        System.out.println("    _______________________________________________________");
        System.out.println("     Here are the deadlines/events in your list that's due/occurring :");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline deadline) {
                if (deadline.getDeadline().equals(searchDate)) {
                    System.out.println("     " + (i + 1) + "." + tasks.get(i));
                }
            } else if (tasks.get(i) instanceof Event event) {
                if (event.getEndEvent().equals(searchDate)
                        || event.getStartEvent().equals(searchDate)
                        || (event.getStartEvent().isBefore(searchDate)
                        && event.getEndEvent().isAfter(searchDate))) {
                    System.out.println("     " + (i + 1) + "." + tasks.get(i));
                }
            }
        }
        System.out.println("    _______________________________________________________");
    }

    public void showFindList(List<Task> tasks, String keyword) {
        System.out.println("    _______________________________________________________");
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().contains(keyword)) {
                System.out.println("     " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println("    _______________________________________________________");
    }
}
