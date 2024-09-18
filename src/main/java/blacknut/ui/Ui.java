package blacknut.ui;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The Ui class handles user interaction by displaying messages and reading input.
 */
class Ui {
    private static final String LOGO = " ____  _               _     _   _       _   _   \n"
            + "|  _ \\| |             | |   | \\ | |     | | | |  \n"
            + "| |_) | | __ _ _ __ __| |   |  \\| | __ _| |_| |_ \n"
            + "|  _ <| |/ _` | '__/ _` |   | . ` |/ _` | __| __|\n"
            + "| |_) | | (_| | | | (_| |_  | |\\  | (_| | |_| |_ \n"
            + "|____/|_|\\__,_|_|  \\__,_(_) |_| \\_|\\__,_|\\__|\\__|\n";


    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Blacknut");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public String getWelcomeMessage() {
        return "Hello from\n" + LOGO
                + "\n____________________________________________________________\n"
                + " Hello! I'm Blacknut\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
    }

    /**
     * Returns a reminder message for tasks (Deadlines or Events) that are due or happening today.
     *
     * @param tasks The list of tasks to check.
     * @return A string reminding the user of tasks happening today.
     */
    public String getReminderMessage(ArrayList<Task> tasks) {
        LocalDate today = LocalDate.now();
        StringBuilder reminderMessage = new StringBuilder("Reminder: Tasks scheduled for today:\n");

        boolean hasReminders = false;

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDate().equals(today)) {
                    reminderMessage.append(" - Deadline: ").append(deadline).append("\n");
                    hasReminders = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getDate().equals(today)) {
                    reminderMessage.append(" - Event: ").append(event).append("\n");
                    hasReminders = true;
                }
            }
        }

        if (!hasReminders) {
            return "No tasks due or events scheduled for today.";
        }

        return reminderMessage.toString();
    }

    public String getGoodbyeMessage() {
        return " Bye. Hope to see you again soon!";
    }

    public String getTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return " The list is empty.";
        }
        StringBuilder sb = new StringBuilder(" Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String getMarkedTask(Task task, boolean markAsDone) {
        if (markAsDone) {
            return " Nice! I've marked this task as done:\n   " + task;
        } else {
            return " OK, I've marked this task as not done yet:\n   " + task;
        }
    }

    public String getAddedTask(Task task, int totalTasks) {
        return " Got it. I've added this task:\n   " + task + "\n Now you have " + totalTasks + " tasks in the list.";
    }

    public String getDeletedTask(Task task, int totalTasks) {
        return " Noted. I've removed this task:\n   " + task + "\n Now you have " + totalTasks + " tasks in the list.";
    }

    public String getMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return " No tasks match your search.";
        }
        StringBuilder sb = new StringBuilder(" Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String getError(String message) {
        return " ☹ OOPS!!! " + message;
    }

    public String getLine() {
        return "____________________________________________________________";
    }

}
