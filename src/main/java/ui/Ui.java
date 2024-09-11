package ui;

import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a CLI UI.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the command entered by the user from the CLI.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner used to read user input.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Shows the welcome message on the CLI.
     */
    public void showWelcome() {
        System.out.println("___________________________________________________________");
        System.out.println(" Hello! I'm Friday");
        System.out.println(" What can I do for you?");
        System.out.println("___________________________________________________________");
    }

    /**
     * Shows the goodbye message on the CLI.
     */
    public void showGoodbye() {
        System.out.println("___________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("___________________________________________________________");
    }

    /**
     * Shows the list of tasks on the CLI.
     *
     * @param tasks The list of tasks.
     */
    public void showTaskList(List<Task> tasks) {
        System.out.println("    _______________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("    _______________________________________________________");
    }

    /**
     * Shows the task added message on the CLI.
     *
     * @param task The task added.
     * @param size The size of the task list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("    _______________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
        System.out.println("    _______________________________________________________");
    }

    /**
     * Shows the task removed message on the CLI.
     *
     * @param task The task removed.
     * @param size The size of the task list.
     */
    public void showTaskRemoved(Task task, int size) {
        System.out.println("    _______________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
        System.out.println("    _______________________________________________________");
    }

    /**
     * Shows the task marked message on the CLI.
     *
     * @param task The task marked.
     */
    public void showTaskMarked(Task task) {
        System.out.println("    _______________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        System.out.println("    _______________________________________________________");
    }

    /**
     * Shows the task unmarked message on the CLI.
     *
     * @param task The task unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("    _______________________________________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
        System.out.println("    _______________________________________________________");
    }

    /**
     * Shows the general error message on the CLI.
     */
    public void showError(String message) {
        System.out.println("    _______________________________________________________");
        System.out.println("     " + "OOPS!!! " + message);
        System.out.println("    _______________________________________________________");
    }

    /**
     * Shows the loading error message on the CLI.
     */
    public void showLoadingError() {
        System.out.println("    _______________________________________________________");
        System.out.println("     An error occurred while loading tasks.");
        System.out.println("    _______________________________________________________");
    }

    /**
     * Shows the search list on the CLI.
     *
     * @param tasks The list of tasks.
     * @param searchDate The search date.
     */
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

    /**
     * Shows the find list on the CLI.
     *
     * @param tasks The list of tasks.
     * @param keyword The keyword to search for.
     */
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

    /**
     * Shows the sorted deadlines on the CLI.
     *
     * @param deadlines The list of deadlines.
     */
    public void showSortedDeadlines(List<Deadline> deadlines) {
        System.out.println("    _______________________________________________________");
        if (deadlines.isEmpty()) {
            System.out.println("     You have no deadlines in your list.");
        } else {
            System.out.println("     Here are the deadlines in your list, sorted by date:");
            for (int i = 0; i < deadlines.size(); i++) {
                System.out.println("     " + (i + 1) + "." + deadlines.get(i));
            }
        }
        System.out.println("    _______________________________________________________");
    }
}
