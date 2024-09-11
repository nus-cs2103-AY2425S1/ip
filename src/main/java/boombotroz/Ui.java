package boombotroz;
import java.time.LocalDate;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    /**
     * Prints all the task from task list.
     *
     * @param taskList list of all the tasks.
     * @return List of all tasks.
     */
    public String printAll(TaskList taskList) {
        String s = "";
        s += "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            s += String.format("%d.%s\n", i + 1, taskList.getTask(i));
        }
        return s;
    }

    /**
     * Returns message after marking task.
     *
     * @param taskList list of tasks.
     * @param index position of task to be marked.
     * @return Message after marking task.
     */
    public String markTaskMessage(TaskList taskList, int index) {
        String s = "";
        s += "Nice! I've marked this as done:\n";
        s += String.format("  %s", taskList.getTask(index));
        return s;
    }

    /**
     * Returns message after unmarking task.
     *
     * @param taskList list of tasks.
     * @param index position of task to be unmarked.
     * @return Message after unmarking task.
     */
    public String unmarkTaskMessage(TaskList taskList, int index) {
        String s = "";
        s += "OK, I've marked this task as not done yet:\n";
        s += String.format("  %s", taskList.getTask(index));
        return s;
    }

    /**
     * Returns message after deleting task.
     *
     * @param taskList list of tasks.
     * @param index position of task to be deleted.
     * @return Message after deleting task.
     */
    public String deleteTaskMessage(TaskList taskList, int index) {
        String s = "";
        s += "Noted. I've removed this task:\n";
        s += String.format("  %s\n", taskList.getTask(index));
        s += String.format("Now you have %d tasks in the list.", taskList.getSize() - 1);
        return s;
    }

    /**
     * Returns message after finding matching task(s).
     *
     * @param taskList list of tasks.
     * @param word word that appear in the task(s).
     * @return Message after finding task(s).
     */
    public String findTaskMessage(TaskList taskList, String word) {
        String s = "";
        s += "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            Task curr = taskList.getTask(i);
            if (curr.getDescription().matches(word)) {
                s += String.format("%d.%s\n", i, curr);
            }
        }
        return s;
    }

    /**
     * Returns message after creating task.
     *
     * @param createdTask task that was created.
     * @param taskList list of tasks.
     * @return Message after creating task.
     */
    public String createTaskMessage(Task createdTask, TaskList taskList) {
        String s = "";
        s += "Got it. I've added this task:\n";
        s += String.format("  %s\n", createdTask);
        s += String.format("Now you have %d tasks in the list.", taskList.getSize());
        return s;
    }

    /**
     * Throws exception when input invalid.
     */
    public void isInvalid() throws BoomException {
        throw new BoomException("That's nonsense !!");

    }

    /**
     * Throws exception when no task details.
     *
     * @param taskInput task details.
     * @throws BoomException If no task details given.
     */
    public void hasTask(String taskInput) throws BoomException {
        if (taskInput.isEmpty()) {
            throw new BoomException(
                    "You can't do nothing !!");
        }

    }

    /**
     * Throws exception when no deadline.
     *
     * @param taskInput task details.
     * @throws BoomException If no deadline given.
     */
    public void hasEnd(String taskInput) throws BoomException {
        if (!taskInput.contains(" /by ")) {
            throw new BoomException(
                    "You need a deadline !!");
        }

    }

    /**
     * Throws exception when invalid time period given.
     *
     * @param taskInput task details.
     * @throws BoomException If invalid time period given.
     */
    public void hasStartEnd(String taskInput) throws BoomException {
        if (!taskInput.contains(" /from ")
                || !taskInput.contains(" /to ")) {
            throw new BoomException(
                    "You need a start and end !!");
        }

    }

    /**
     * Throws exception when no task number given.
     *
     * @param markInput mark details.
     * @throws BoomException If no task number given.
     */
    public void hasNumber(String markInput) throws BoomException {
        String[] parts = markInput.split(" ");

        // Check if there is a second part after splitting
        if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
            throw new BoomException(
                    "You need to give a number !!");
        }

    }

    /**
     * Throws exception when invalid range given.
     *
     * @param index task index.
     * @param taskList list of all the tasks
     * @throws BoomException If invalid range given.
     */
    public void isWrongRange(int index, TaskList taskList) throws BoomException {
        if (index + 1 <= 0
                || index + 1 > taskList.getSize()) {
            throw new BoomException(
                    "You are out of range !!");
        }
    }

    /**
     * Throws exception when invalid deadline given.
     *
     * @param d1 task deadline.
     * @param d2 time now.
     * @throws BoomException If invalid deadline given.
     */
    public void isWrongDeadline(LocalDate d1, LocalDate d2) throws BoomException {
        if (d2.isAfter(d1)) {
            throw new BoomException("Your date is wrong !!");
        }

    }

    /**
     * Throws exception when invalid time period given.
     *
     * @param d1 task start time.
     * @param d2 task end time.
     * @param d3 time now.
     * @throws BoomException If invalid time period given.
     */
    public void isWrongEventTime(LocalDate d1, LocalDate d2, LocalDate d3) throws BoomException {
        if (d1.isAfter(d2) || d3.isAfter(d2)) {
            throw new BoomException("Your date is wrong !!");
        }

    }

    /**
     * Throws exception when no description given.
     *
     * @param wordInput task description.
     * @throws BoomException If no description given.
     */
    public void hasKeyWord(String wordInput) throws BoomException {
        String[] parts = wordInput.split(" ");
        if (parts.length < 2) {
            throw new BoomException("You need a keyword !!");
        }
    }

}
