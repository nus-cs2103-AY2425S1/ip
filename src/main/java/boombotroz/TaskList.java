package boombotroz;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the task list and its operands.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns size of task list.
     */
    public int size() {
        return taskList.size();
    }


    /**
     * Returns the task from the task list at that index.
     *
     * @param index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Adds task to task list.
     * Prints message about the task that was just added and number of tasks in list.
     *
     * @param task task to be added
     */
    public void addTaskWithMessage(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format(
                "  %s", task));
        System.out.println(String.format(
                "Now you have %d tasks in the list.",
                taskList.size()));
    }

    /**
     * Adds task to task list.
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes task from task list.
     * Prints message about the task that was just deleted and number of tasks in list.
     *
     * @param input input given by user.
     * @param ui handles errors that may occur.
     * @throws BoomException If position not given OR position out of range.
     */
    public void deleteTask(String input, Ui ui) throws BoomException {
        // check if there is a given task number to delete
        ui.isTaskNumber(input);

        int index = Integer.parseInt(
                input.split(" ")[1]) - 1;

        // check if the task number falls within the task list range
        ui.wrongRange(index, this);

        System.out.println("Noted. I've removed this task:");
        System.out.println(String.format(
                "  %s", taskList.get(index)));
        taskList.remove(taskList.get(index));
        System.out.println(String.format(
                "Now you have %d tasks in the list.",
                taskList.size()));
    }

    /**
     * Marks task from task list.
     * Prints message about the task that was just marked.
     *
     * @param input input given by user.
     * @param ui handles errors that may occur.
     * @throws BoomException If position not given OR position out of range.
     */
    public void markTask(String input, Ui ui) throws BoomException {
        // check if there is a given task number to mark
        ui.isTaskNumber(input);

        int index = Integer.parseInt(
                input.split(" ")[1]) - 1;

        // check if the task number falls within the task list range
        ui.wrongRange(index, this);

        taskList.get(index).setMark(true);
        System.out.println("Nice! I've marked this as done:");
        System.out.println(String.format(
                "  %s", taskList.get(index)));

    }

    /**
     * Unmark task from task list.
     * Prints message about the task that was just unmarked.
     *
     * @param input input given by user.
     * @param ui handles errors that may occur.
     * @throws BoomException If position not given OR position out of range.
     */
    public void unmarkTask(String input, Ui ui) throws BoomException {
        // check if there is a given task number to unmark
        ui.isTaskNumber(input);

        int index = Integer.parseInt(
                input.split(" ")[1]) - 1;

        // check if the task number falls within the task list range
        ui.wrongRange(index, this);

        taskList.get(index).setMark(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(String.format(
                "  %s", taskList.get(index)));
    }

    /**
     * Prints all the task from task list.
     */
    public void printAll() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format(
                    "%d.%s", i + 1, taskList.get(i)));
        }
    }

    /**
     * Returns all the task from task list.
     */
    public String getAll() {
        String s = "";
        for (int i = 0; i < taskList.size(); i++) {
            s = s.concat(taskList.get(i) + "\n");
        }
        return s;
    }

}
