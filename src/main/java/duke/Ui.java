package duke;

import duke.Exception.DukeException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that deals with interactions in terminal.
 */
public class Ui {
    private final String NAME = "Nameless";
    private final String GREETINGS = "Hello, I'm " + NAME + "\n" + "What can I do for you?";
    private final String LEFT_MESSAGE = "Now you have ";
    private final String RIGHT_MESSAGE = " task left";
    private final String GOODBYE = "Bye. Hope to see you again!";
    private final String SHOW_ADD_TASK = "Got it. I've added this task:";
    private final String REMOVE_TASK = "Noted. I've removed this task:";
    private final String NEW_LINE = "\n";
    private final String NEW_LINE_TAB = "\n\t";

    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM-dd-yyyy");

    public String greetings() {
        return NEW_LINE + GREETINGS + NEW_LINE;
    }

    public String goodbye() {
        return NEW_LINE + GOODBYE + NEW_LINE;
    }

    /**
     * Shows the message printed when a task is added.
     */
    public String showAddTask(TaskList tasks) {
        return NEW_LINE + SHOW_ADD_TASK
                + NEW_LINE_TAB + tasks.get(tasks.size() - 1).toString() + NEW_LINE
                + LEFT_MESSAGE + tasks.size() + RIGHT_MESSAGE + NEW_LINE;
    }

    /**
     * Shows the message printed when a task is deleted.
     */
    public String showDeleteTask(TaskList tasks, int index) throws DukeException {
        Task task = tasks.get(index);
        tasks.deleteTask(index);

        return NEW_LINE + REMOVE_TASK
                + NEW_LINE_TAB + task.toString() + NEW_LINE
                + LEFT_MESSAGE + tasks.size() + RIGHT_MESSAGE + NEW_LINE;
    }

    /**
     * Shows the entire list when user input "list".
     */
    public String showList(TaskList tasks) {
        StringBuilder sb = new StringBuilder(NEW_LINE + "Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n").append(i + 1).append(". ").append(tasks.get(i).toString());
        }
        sb.append(NEW_LINE);

        return sb.toString();
    }

    /**
     * Shows the message printed when a task is marked.
     */
    public String showMarkTask(TaskList tasks, int index) {
        return NEW_LINE + tasks.get(index).markTask() + "\n" + NEW_LINE;
    }

    /**
     * Shows the message printed when a task is unmarked.
     */
    public String showUnmarkTask(TaskList tasks, int index) {
        return NEW_LINE + tasks.get(index).unMarkTask() + NEW_LINE;
    }

    /**
     * Returns the task that matches the keyword
     */
    public String showFindTask(TaskList tasks, String keyword) {
        StringBuilder sb = new StringBuilder(NEW_LINE + "Here are the matching tasks in your list:");
        int count = 0;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                count++;
                sb.append(NEW_LINE).append(count).append(". ").append(tasks.get(i).toString());
            }
        }
        sb.append(NEW_LINE);

        return sb.toString();
    }

    public String showViewTask(TaskList tasks, LocalDate date) {
        StringBuilder sb = new StringBuilder(NEW_LINE + "Here are the tasks on " + date + " in your list:");
        int count = 0;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(date.format(DATE_FORMAT))) {
                count++;
                sb.append(NEW_LINE).append(count).append(". ").append(tasks.get(i).toString());
            }
        }
        sb.append(NEW_LINE);

        return sb.toString();
    }
}

