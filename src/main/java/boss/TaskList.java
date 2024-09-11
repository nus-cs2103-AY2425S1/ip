package boss;

import boss.exceptions.BossException;
import boss.exceptions.EmptyTaskInputException;
import boss.exceptions.NonExistentTaskException;
import boss.tasks.Deadline;
import boss.tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the class that contains the task list and
 * has operations to add/delete tasks from the list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        assert task != null : "task cannot be null";
        tasks.add(task);
    }

    /**
     * Unmarks a task from the list.
     * @param task the task that the user wants to unmark
     * @return the text to replace text in file
     */
    public String[] unmark(String task) throws BossException {
        // gets the task number to unmark
        String taskNum = task.replaceAll("[^0-9]", "");
        if (taskNum.isEmpty()) {
            throw new EmptyTaskInputException("unmark");
        }
        int num = Integer.parseInt(taskNum);
        if (tasks.size() < num) {
            throw new NonExistentTaskException(num);
        }
        Task item = tasks.get(num - 1);
        item.markAsUnDone();

        String newFileData = getAll(tasks);

        return new String[] {newFileData, item.toString()};
    }

    /**
     * Unmarks a task from the list.
     * @param task the task that the user wants to mark
     * @return the text to replace text in file
     */
    public String[] mark(String task) throws BossException {
        // gets the task number to mark
        String taskNum = task.replaceAll("[^0-9]", "");

        if (taskNum.isEmpty()) {
            throw new EmptyTaskInputException("mark");
        }
        int num = Integer.parseInt(taskNum);
        if (tasks.size() < num) {
            throw new NonExistentTaskException(num);
        }
        Task item = tasks.get(num - 1);
        item.markAsDone();

        String newFileData = getAll(tasks);
        return new String[] {newFileData, item.toString()};
    }

    /**
     * Deletes a task from the list.
     * @param task the task that the user wants to delete
     * @return the text to replace text in file
     */
    public String[] delete(String task) throws BossException {
        // gets the task number to remove, i.e. delete
        String taskNum = task.replaceAll("[^0-9]", "");
        if (taskNum.isEmpty()) {
            throw new EmptyTaskInputException("delete");
        }
        int num = Integer.parseInt(taskNum);
        if (tasks.size() < num) {
            throw new NonExistentTaskException(num);
        }
        Task item = tasks.remove(num - 1);
        String newFileData = getAll(tasks);

        return new String[] {newFileData, item.toString()};
    }

    public String remind() {
        String s = "";
        for (Task task : tasks) {
            String type = task.getType();
            if (type.equals("deadline")) {
                Deadline deadline = (Deadline) task;
                if (deadline.checkDateDifference() || deadline.checkTimeDifference()) {
                    s = s + task + '\n';
                }
            }
        }
        if (s.isEmpty()) {
            return "You have no upcoming deadlines!";
        }
        return s;
    }

    /**
     * used to rewrite text file
     *
     * @param tasks list of tasks
     * @return a String containing all the tasks
     */
    private static String getAll(ArrayList<Task> tasks) {
        String s = "";
        for (Task str : tasks) {
            s += "" + str + '\n';
        }
        return s;
    }

    /**
     * Returns user messages to print onto screen for GUI.
     *
     * @return String containing messages for user.
     */
    public String printAddTask(Task task) {
        String toPrint = "";
        toPrint = toPrint + "Got it! I've added this task now!" + "\n";
        toPrint = toPrint + task + "\n";
        toPrint = toPrint + "Now you have " + tasks.size() + " tasks in the list." + "\n";
        return toPrint;
    }

    /**
     * Returns the Chatbot's response when user marks a task.
     *
     * @param task string representation of the task user wants to mark
     * @return String containing chatbot's response
     */
    public String printMark(String task) {
        String toPrint = "";
        toPrint = "Nice! I have marked this task as done!" + "\n";
        toPrint = toPrint + task + "\n";
        return toPrint;
    }

    /**
     * Returns the Chatbot's response when user unmarks a task.
     *
     * @param task string representation of the task user wants to unmark
     * @return String containing chatbot's response
     */
    public String printUnmark(String task) {
        String toPrint = "";
        toPrint = "Nice! I have marked this task as undone!" + "\n";
        toPrint = toPrint + task + "\n";
        return toPrint;
    }

    /**
     * Returns the Chatbot's response when user deletes a task.
     *
     * @param task string representation of the task user wants to delete
     * @return String containing chatbot's response
     */
    public String printDelete(String task) {
        String toPrint = "";
        toPrint = "Ok! This task has been removed!" + "\n";
        toPrint = toPrint + task + "\n";
        return toPrint;
    }

    /**
     * Find the tasks that match the given word (for GUI).
     *
     * @param word the word user wants to find matching tasks for/
     * @return String containing tasks that match the word.
     */
    public String findTask(String word) {
        int i = 0;
        String result = "";
        for (Task str : tasks) {
            if (str.getDescription().contains(word)) {
                if (i == 0) {
                    result = result + "Here are the matching tasks in your list: " + "\n";
                    i++;
                }
                result = result + str + "\n";
            }
        }
        if (i == 0) {
            return "There are no matching tasks :(";
        }
        return result;
    }

}

