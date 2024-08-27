package TaskList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import Data.Deadlines;
import Data.Events;
import Exceptions.InvalidTaskNumberException;
import Data.Task;
public class TaskList {
    LinkedList<Task> tasks;
    int taskCount = 0;

    public TaskList(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new LinkedList<Task>();
    }

    /**
     * return number of tasks in TaskList
     * @return
     */
    public int taskCount() {
        return this.taskCount;
    }
    /**
     * Add new Data.Task to the TaskList.TaskList
     * @param newTask
     * @return
     */
    public String add(Task newTask) {
        tasks.add(newTask);
        taskCount ++;
        return " Got it. I've added this task:\n"
                + "   " + newTask.toString() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n";
    }

    /**
     * Mark task i as done
     * @param i
     * @return
     * @throws InvalidTaskNumberException
     */
    public String mark(int i) throws InvalidTaskNumberException {
        if (taskCount == 0) {
            throw new InvalidTaskNumberException();
        }
        if (i <= 0 || i > taskCount) {
            throw new InvalidTaskNumberException(i, taskCount);
        }
        tasks.get(i - 1).mark();
        return " Nice! I've marked this task as done:\n"
                + "   " + tasks.get(i - 1).toString();

    }

    /**
     * Unmark the task i
     * @param i
     * @return
     * @throws InvalidTaskNumberException
     */
    public String unMark(int i) throws InvalidTaskNumberException {
        if (taskCount == 0) {
            throw new InvalidTaskNumberException();
        }
        if (i <= 0 || i > taskCount) {
            throw new InvalidTaskNumberException(i, taskCount);
        }
        tasks.get(i - 1).unMark();
        return " OK, I've marked this task as not done yet:\n"
                + "   " + tasks.get(i - 1).toString();

    }

    /**
     * Delete task i
     * @param i
     * @return
     * @throws InvalidTaskNumberException
     */
    public String delete(int i) throws InvalidTaskNumberException {
        if (taskCount == 0) {
            throw new InvalidTaskNumberException();
        }
        if (i <= 0 || i >= taskCount) {
            throw new InvalidTaskNumberException(i, taskCount);
        }
        taskCount --;
        String s = " Noted. I've removed this task:\n"
                + "   " + tasks.get(i - 1).toString() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n";
        tasks.remove(i - 1);
        return s;
    }

    /**
     * Return String representation of all tasks in the list
     * @return
     */
    public String readTask() {
        String s = " Here are the tasks in your list:\n";
        for (int i = 1; i <= taskCount; i ++) {
            s += " " + i + ". " + tasks.get(i - 1).toString() + "\n";
        }
        return s;
    }

    /**
     * Return String representation of tasks in the list that are before due
     * @param time
     * @return
     */
    public String dueOn(String time) {
        LocalDateTime due = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        String s = " Here are the tasks in your list that ends before the due:\n";
        int i = 1;
        for (Task t : tasks) {
            if (t instanceof Deadlines || t instanceof Events) {
                if (t.endTime().isBefore(due)) {
                    s += " " + i + ". " + t.toString() + "\n";
                }
            }
        }
        return s;
    }

    public String brief() {
        String s = "";
        for (int i = 1; i <= taskCount; i ++) {
            s += " " + i + ". " + tasks.get(i - 1).brief() + "\n";
        }
        return s;
    }

}
