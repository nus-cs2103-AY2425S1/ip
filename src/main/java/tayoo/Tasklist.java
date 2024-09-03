package tayoo;

import java.util.List;
import java.util.ArrayList;

import tayoo.tasks.Task;

//Contains the digital tasklist that is used by the Tayoo chatbot while the bot is running.
public class Tasklist {

    /**
     * The list of tasks in the task manager. Variable is final because it should not be reassigned to another value.
     * The capacity of the tasklist should not exceed 100 tasks.
     */
    private final ArrayList<Task> TASKLIST = new ArrayList<>(100);

    /**
     * Constructs a new Tasklist object from a list of Tasks. It runs a loop to access each method of the list of Tasks
     * provided and adds them to the TASKLIST.
     *
     * @param taskList The provided list of tasks. Any argument should extend from List<Task>.
     */
    public Tasklist(List<Task> taskList) {
        int size = taskList.size();

        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.TASKLIST.add(taskList.get(i));
            }
        }
    }

    /**
     * Adds a new task to the TASKLIST. Fails if the TASKLIST is at 100 tasks, maintaining the TASKLIST's limit of
     * 100 tasks.
     *
     * @param task The task that is to be added to the TASKLIST
     * @return returns {@code true} if task is successfully added. Returns {@code false} if the task is not successfully
     * added because the TASKLIST is at full capacity
     */
    public boolean addTask(Task task) {
        if (TASKLIST.size() >= 100) {
            return false;
        } else {
            TASKLIST.add(task);
            return true;
        }
    }

    /**
     * Deletes a task from the TASKLIST. The task is identified by accessing the TASKLIST using the task's index.
     *
     * @param taskNumber The zero-based index of the task that is to be removed
     * @return {@code true} if the task was found and successfully removed from TASKLIST, {@code false} if the index
     * provided exceeds the TASKLIST size or is negative
     * @throws IndexOutOfBoundsException if the index is out of bounds for the TASKLIST
     */
    public boolean deleteTask(int taskNumber) {
        if (taskNumber < 0) {
            return false;
        }
        Task task = TASKLIST.get(taskNumber);
        return TASKLIST.remove(task);
    }

    /**
     * Returns the string representation of all the tasks in the TASKLIST. It iterates through the TASKLIST and calls
     * the {@code toString()} method on each task. It appends the Task's number then the Task's string representation.
     *
     * @return String representation of all the tasks in the TASKLIST
     */
    public String printTaskList() {
        StringBuilder toPrint = new StringBuilder("Here are the tasks in your list: \n");
        int length = TASKLIST.size();

        for (int i = 0; i < length; i++) {
            toPrint.append(i + 1).append(". ").append(TASKLIST.get(i).toString()).append("\n");
        }

        return toPrint.toString();
    }

    /**
     * Deletes all the task in the tasklist, effectively clearing it. This operation cannot be undone and will result
     * in the permanent loss of all tasks currently in the list.
     *
     * @return {@code true} if the TASKLIST was successfully cleared; {@code false} if the TASKLIST contained no tasks
     * to clear
     */
    public boolean deleteAll() {
        int length = TASKLIST.size();
        if (length > 0) {
            TASKLIST.subList(0, length).clear();
            return true;
        }
        return false;
    }

    /**
     * Marks the provided taskNumber as completed. Utilises the {@code markAsDone()} method within the Task class. This
     * currently only changes the string representation of the task.
     *
     * @param taskNumber the zero-based index of the task inside TASKLIST
     * @return {@code true} if the TASKLIST was successfully marked; {@code false} if the TASKLIST does not contain the
     * task or the taskNumber was negative
     */
    public boolean markTask(int taskNumber) {
        if (taskNumber < 0) {
            return false;
        }
        return TASKLIST.get(taskNumber).markAsDone();
    }

    /**
     * Marks the provided taskNumber as incomplete. Utilises the {@code unmark()} method within the Task class. This
     * currently only changes the string representation of the task.
     *
     * @param taskNumber the zero-based index of the task inside TASKLIST
     * @return {@code true} if the TASKLIST was successfully marked; {@code false} if the TASKLIST does not contain the
     * task or the taskNumber was negative
     */
    public boolean unmarkTask(int taskNumber) {
        if (taskNumber < 0) {
            return false;
        }
        return TASKLIST.get(taskNumber).unmark();
    }

    public int getSize() {
        return TASKLIST.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.TASKLIST;
    }

    public String getTaskStr(int taskNumber) {
        if (taskNumber < 0) {
            return null;
        }
        return TASKLIST.get(taskNumber).toString();
    }

}
