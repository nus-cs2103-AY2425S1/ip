package duke;

import duke.Exception.DukeException;
import duke.task.Task;

/**
 * A class that deals with interactions in terminal.
 */
public class Ui {
    private final String LINE = "____________________________________________________";
    private final String NAME = "duke";
    private final String GREETINGS = "Hello, I'm " + NAME + "\n" + "What can I do for you?";
    private final String GOODBYE = "Bye. Hope to see you again!";

    public String greetings() {
        return LINE + "\n" + GREETINGS + "\n" + LINE;
    }

    public String goodbye() {
        return LINE + "\n" + GOODBYE + "\n" + LINE;
    }

    /**
     * Show the message printed when a task is added.
     */
    public String showAddTask(TaskList tasks) {
        return LINE + "\n" + "Got it. I've added this task:"
                + "\n     " + tasks.get(tasks.size() - 1).toString() + "\n"
                + "Now you have " + tasks.size() + " task left \n" + LINE;
    }

    /**
     * Show the message printed when a task is deleted.
     */
    public String showDeleteTask(TaskList tasks, int index) throws DukeException {
        Task task = tasks.get(index);
        tasks.deleteTask(index);
        return LINE + "\n" + "Noted. I've removed this task:"
                + "\n     " + task.toString() + "\n"
                + "Now you have " + tasks.size() + " task left \n" + LINE;
    }

    /**
     * Show the entire list when user input "list".
     */
    public String showList(TaskList tasks) {
        StringBuilder sb = new StringBuilder(LINE + "\n Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n").append(i + 1).append(". ").append(tasks.get(i).toString());
        }
        sb.append("\n" + LINE);
        return sb.toString();
    }

    /**
     * Show the message printed when a task is marked.
     */
    public String showMarkTask(TaskList tasks, int index) {
        return LINE + "\n" + tasks.get(index).markTask() + "\n" + LINE;
    }

    /**
     * Show the message printed when a task is unmarked.
     */
    public String showUnmarkTask(TaskList tasks, int index) {
        return LINE + "\n" + tasks.get(index).unMarkTask() + "\n" + LINE;
    }

    /**
     * Returns the task that matches the keyword
     */
    public String showFindTask(TaskList tasks, String keyword) {
        StringBuilder sb = new StringBuilder(LINE + "\n Here are the matching tasks in your list:");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                count++;
                sb.append("\n").append(count).append(". ").append(tasks.get(i).toString());
            }
        }
        sb.append("\n" + LINE);
        return sb.toString();
    }
}

