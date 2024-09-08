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
     * @return message of task that is deleted.
     * @throws BoomException If position not given OR position out of range.
     */
    public String deleteTask(String input, Ui ui) throws BoomException {
        // check if there is a given task number to delete
        ui.isTaskNumber(input);

        int index = Integer.parseInt(
                input.split(" ")[1]) - 1;

        // check if the task number falls within the task list range
        ui.wrongRange(index, this);
        String s = "";
        s += "Noted. I've removed this task:\n";
        s += String.format("  %s\n", taskList.get(index));
        taskList.remove(taskList.get(index));
        s += String.format("Now you have %d tasks in the list.", taskList.size());
        return s;
    }

    /**
     * Marks task from task list.
     * Prints message about the task that was just marked.
     *
     * @param input input given by user.
     * @param ui handles errors that may occur.
     * @return Message after task is marked.
     * @throws BoomException If position not given OR position out of range.
     */
    public String markTask(String input, Ui ui) throws BoomException {
        // check if there is a given task number to mark
        ui.isTaskNumber(input);

        int index = Integer.parseInt(
                input.split(" ")[1]) - 1;

        // check if the task number falls within the task list range
        ui.wrongRange(index, this);
        taskList.get(index).setMark(true);
        String s = "";
        s += "Nice! I've marked this as done:\n";
        s += String.format("  %s", taskList.get(index));
        return s;
    }

    /**
     * Unmark task from task list.
     * Prints message about the task that was just unmarked.
     *
     * @param input input given by user.
     * @param ui handles errors that may occur.
     * @return Message after task is unmarked.
     * @throws BoomException If position not given OR position out of range.
     */
    public String unmarkTask(String input, Ui ui) throws BoomException {
        // check if there is a given task number to unmark
        ui.isTaskNumber(input);

        int index = Integer.parseInt(
                input.split(" ")[1]) - 1;

        // check if the task number falls within the task list range
        ui.wrongRange(index, this);
        String s = "";
        taskList.get(index).setMark(false);
        s += "OK, I've marked this task as not done yet:\n";
        s += String.format("  %s", taskList.get(index));
        return s;
    }

    /**
     * Prints all the task from task list.
     */
    public String printAll() {
        String s = "";
        s += "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            s += String.format("%d.%s\n", i + 1, taskList.get(i));
        }
        return s;
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
