package tudee.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import tudee.task.Deadline;
import tudee.task.Events;
import tudee.task.Task;
import tudee.task.TaskList;

/**
 * The Ui class handles the interaction with the user.
 * It manages input and output, displaying messages to the user,
 */
public class Ui {
    private static final String STRAIGHTLINE = "____________________________________________________________ \n";
    private static final int INDEX_OFFSET = 1;
    private final Scanner sc;

    /**
     * Constructs a new Ui object and initializes the scanner
     * for reading user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a straight line to the console to separate sections of output.
     */
    public void showLine() {
        System.out.println(STRAIGHTLINE);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Tudee, your chatbot bestie! \nHow can I help you today? :) \n");
        showLine();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon! Don't forget about me :( \n";
    }

    /**
     * Displays the current list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String showTaskList(List<Task> tasks) {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += i + INDEX_OFFSET + ". " + tasks.get(i) + "\n";
        }
        return output;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public String showMark(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public String showUnmark(Task task) {
        return "Ok, I've marked this task as not done yet: \n" + task;
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task  The task that has been added.
     * @param count The total number of tasks in the list after adding the new task.
     */
    public String showAdd(Task task, int count) {
        String output = "";
        output += "Got it. I've added this task: \n  " + task + "\n";
        output += "Now you have " + count + " tasks in the list. \n";
        return output;
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task  The task that has been deleted.
     * @param count The total number of tasks in the list after deleting the task.
     */
    public String showDelete(Task task, int count) {
        String output = "";
        output += "Noted. I've removed this task: \n" + task + "\n";
        output += "Now you have " + count + " tasks in the list. \n";
        return output;
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user as a string.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays the matching tasks name to the user.
     * @param tasks The tasks we want to be displayed.
     */
    public String showMatchingTasks(TaskList tasks) {
        String output = "";
        for (int i = 0; i < tasks.numOfTasks(); i++) {
            output += i + INDEX_OFFSET + ". " + tasks.getTask(i) + "\n";
        }
        return output;
    }

    public String showReminders(TaskList tasks) {
        LocalDate today = LocalDate.now();
        boolean hasReminders = false;
        String output = "\n" + "REMINDER:" + "\n";
        for (int i = 0; i < tasks.numOfTasks(); i++) {
            Task task = tasks.getTask(i);
            int index = i + INDEX_OFFSET;
            if (task instanceof Deadline) {
                Deadline currentTask = (Deadline) task;
                if (currentTask.isDueSoon(today)) {
                    output += index + ". " + currentTask.getDescription() + "is due tomorrow!" + "\n";
                    hasReminders = true;
                }
            } else if (task instanceof Events) {
                Events currentTask = (Events) task;
                if (currentTask.isDueSoon(today)) {
                    output += index + ". " + currentTask.getDescription() + "is due tomorrow!" + "\n";
                    hasReminders = true;
                }
            }
        }
        return hasReminders ? output : "";
    }
    /**
     * Closes the Scanner resource used for reading user input.
     * This should be called when the user terminates the chatbot.
     */
    public void close() {
        if (sc != null) {
            sc.close();
        }
    }
}
