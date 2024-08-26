package friday;

import friday.task.Deadline;
import friday.task.Event;
import friday.task.Task;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ui {
    public void greet() {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Hello! I'm Friday\n" +
                        "     What can I do for you?\n" +
                        "    ____________________________________________________________"
        );
    }

    public void showError(String message) {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     " + message + "\n" +
                        "    ____________________________________________________________"
        );
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading file.");
    }

    public void showAddedTask(Task task, int noOfTasks) {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "       " + task + "\n" +
                        "     Now you have " + noOfTasks + " tasks in the list.\n" +
                        "    ____________________________________________________________"
        );
    }

    public void showDeletedTask(Task task, int noOfTasks) {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Noted. I've removed this task:\n" +
                        "       " + task + "\n" +
                        "     Now you have " + noOfTasks + " tasks in the list.\n" +
                        "    ____________________________________________________________"
        );
    }

    public void showMarkedTask(Task task) {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done:\n" +
                        "       " + task + "\n" +
                        "    ____________________________________________________________"
        );
    }

    public void showUnmarkedTask(Task task) {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     OK, I've marked this task as not done yet:\n" +
                        "       " + task + "\n" +
                        "    ____________________________________________________________"
        );
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("    ____________________________________________________________");
        if (tasks.isTaskListEmpty()) {
            System.out.println("     Your task list is empty.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println("     " + (i + 1) + "." + tasks.getTask(i));
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    public void showSpecificTasks(TaskList tasks, LocalDate date) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are your tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        int count = 0;

        for (Task task : tasks.getTasks()) {
            if (task instanceof Deadline) {
                if (((Deadline) task).getBy().equals(date)) {
                    System.out.println("     " + (++count) + ". " + task);
                }
            } else if (task instanceof Event) {
                LocalDate from = ((Event) task).getFrom();
                LocalDate to = ((Event) task).getTo();
                if ((date.equals(from) || date.equals(to)) || (date.isAfter(from) && date.isBefore(to))) {
                    System.out.println("     " + (++count) + ". " + task);
                }
            }
        }

        if (count == 0) {
            System.out.println("     No tasks found on this date.");
        }

        System.out.println("    ____________________________________________________________");
    }

    public void sayGoodbye() {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________"
        );
    }

}
