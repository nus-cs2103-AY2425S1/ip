package tayoo;

import java.util.ArrayList;
import java.util.List;

import tayoo.tasks.Task;

/** Contains the digital tasklist that is used by the Tayoo chatbot while the bot is running. */
public class Tasklist {


    /** Defines the maximum capacity of the tasklist */
    public static final int MAXIMUM_CAPACITY = 100;

    /**
     * The list of tasks in the task manager. Variable is final because it should not be reassigned to another value.
     */
    private final ArrayList<Task> tasklistArray = new ArrayList<>(MAXIMUM_CAPACITY);

    /**
     * Constructs a new Tasklist object from a list of Tasks. It runs a loop to access each method of the list of Tasks
     * provided and adds them to the TASKLIST.
     *
     * @param taskList The provided list of tasks. Any argument should extend from {@code List<Task>}.
     */
    public Tasklist(List<Task> taskList) {
        if (!taskList.isEmpty()) {
            this.tasklistArray.addAll(taskList);
        }
    }

    /**
     * Adds a new task to the TASKLIST. Fails if the TASKLIST is at 100 tasks, maintaining the TASKLIST's limit of
     * 100 tasks.
     *
     * @param task The task that is to be added to the TASKLIST
     * @return returns {@code true} if task is successfully added. Returns {@code false} if the task is not successfully
     *  added because the TASKLIST is at full capacity
     */
    public boolean addTask(Task task) {
        if (tasklistArray.size() >= MAXIMUM_CAPACITY) {
            return false;
        }

        return tasklistArray.add(task);
    }

    /**
     * Deletes a task from the TASKLIST. The task is identified by accessing the TASKLIST using the task's index.
     *
     * @param taskNumber The zero-based index of the task that is to be removed
     * @return the deleted task if the task was successfully removed and {@code null} if not.
     */
    public Task deleteTask(int taskNumber) {
        if (taskNumber < 0 || taskNumber >= tasklistArray.size()) {
            return null;
        }
        return tasklistArray.remove(taskNumber);
    }

    /**
     * Returns the string representation of all the tasks in the TASKLIST. It iterates through the TASKLIST and calls
     * the {@code toString()} method on each task. It appends the Task's number then the Task's string representation.
     *
     * @return String representation of all the tasks in the TASKLIST
     */
    public String printTaskList() {
        StringBuilder toReturn = new StringBuilder("Here are the tasks in your list: \n");
        int tasklistSize = tasklistArray.size();

        for (int i = 0; i < tasklistSize; i++) {
            toReturn.append(i + 1)
                    .append(". ")
                    .append(tasklistArray.get(i).toString())
                    .append("\n");
        }

        return toReturn.toString();
    }

    /**
     * Deletes all the task in the tasklist, effectively clearing it. This operation cannot be undone and will result
     * in the permanent loss of all tasks currently in the list.
     *
     * @return {@code true} if the TASKLIST was successfully cleared; {@code false} if the tasklistArray contained no
     *  tasks to clear
     */
    public boolean deleteAll() {
        int length = tasklistArray.size();
        if (length > 0) {
            tasklistArray.subList(0, length).clear();
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
     *  task or the taskNumber was negative
     */
    public boolean markTask(int taskNumber) {
        if (taskNumber < 0 || taskNumber >= tasklistArray.size()) {
            return false;
        }
        return tasklistArray.get(taskNumber).markAsDone();
    }

    /**
     * Marks the provided taskNumber as incomplete. Utilises the {@code unmark()} method within the Task class. This
     * currently only changes the string representation of the task.
     *
     * @param taskNumber the zero-based index of the task inside TASKLIST
     * @return {@code true} if the TASKLIST was successfully marked; {@code false} if the TASKLIST does not contain the
     *  task or the taskNumber was negative
     */
    public boolean unmarkTask(int taskNumber) {
        if (taskNumber < 0  || taskNumber >= tasklistArray.size()) {
            return false;
        }
        return tasklistArray.get(taskNumber).markAsNotDone();
    }

    public int getSize() {
        return tasklistArray.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasklistArray;
    }

    public String getTaskStr(int taskNumber) {
        if (taskNumber < 0 || taskNumber >= tasklistArray.size()) {
            return null;
        }
        return tasklistArray.get(taskNumber).toString();
    }

    /**
     * For a given input, finds and returns all tasks with the input as a substring within the title/description of the
     * task. The tasks are displayed with each task on a new line.
     *
     * @param substring the substring to be searched for within
     * @return the tasks with the substring inside their title/description compiled into a single string
     */
    public String find(String substring) {
            List<Task> foundTasks = Parser.findTaskInTasklist(substring, this.tasklistArray);

        assert !substring.isEmpty() : "Substring should not be empty";

        if (!foundTasks.isEmpty()) {
            int length = foundTasks.size();
            StringBuilder toReturn = new StringBuilder("Here are the matching tasks in your list: \n");
            for (int i = 0; i < length; i++) {
                toReturn.append(i + 1)
                        .append(". ")
                        .append(foundTasks.get(i).toString())
                        .append("\n");
            }
            return toReturn.toString();
        } else {
            return "Could not find any matching tasks!";
        }
    }
    /**
     * Returns the number of tasks left in the tasklistArray
     *
     * @return String representation of the number of tasks left in the tasklistArray
     */
    public String numberOfTasksLeft() {
        StringBuilder toReturn = new StringBuilder();
        int tasklistSize = tasklistArray.size();
        if (tasklistSize > 1 || tasklistSize == 0) {
            toReturn.append("\n Now you have ").append(tasklistSize).append(" tasks in your list");
        } else {
            toReturn.append("\n Now you have ").append(tasklistSize).append(" task in your list");
        }
        return toReturn.toString();
    }
}
