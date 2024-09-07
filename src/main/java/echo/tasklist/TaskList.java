package echo.tasklist;

import java.util.ArrayList;
import java.util.Collections;

import echo.exception.EchoException;
import echo.task.Task;

/**
 * Represents a list of task
 *
 * @author Ernest Lim
 */
public class TaskList {

    /** ArrayList of the tasks added to TaskList by the user */
    private ArrayList<Task> taskList;
    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the number of task in the taskList
     *
     * @return number of task in taskList
     */
    public int sizeOfTaskList() {
        return this.taskList.size();
    }

    /**
     * Marks task with given value and returns the task that is marked
     *
     * @param value index of the task to be marked in the taskList.
     * @return the task being marked.
     */
    public Task markAndGetTask(String value) {
        int index = Integer.parseInt(value) - 1;
        if (index < 0) {
            throw new EchoException("Sorry! Index cannot be negative, index of the task starts with 1");
        }
        Task task = this.getTask(index);
        task.mark();
        return task;
    }

    /**
     * Unmarks task with given value and returns the task that is unmarked
     *
     * @param value index of the task to be unmarked in the taskList.
     * @return the task being unmarked.
     * @throws EchoException if index is less than 0
     */
    public Task unmarkAndGetTask(String value) {
        int index = Integer.parseInt(value) - 1;
        if (index < 0) {
            throw new EchoException("Sorry! Index cannot be negative, index of the task starts with 1");
        }
        Task task = this.getTask(index);
        task.unmark();
        return task;
    }

    /**
     * Returns the Task at a given index of taskList
     *
     * @param index of the task in taskList
     * @return Task
     * @throws EchoException If index is greater than the total number of tasks in taskList
     */
    public Task getTask(int index) {
        int largestIndex = this.sizeOfTaskList() - 1;

        assert index >= 0 : "index of the task should not be negative";
        assert largestIndex >= 0 : "largest index of the task array cannot be less than 0";

        if (index > largestIndex) {
            throw new EchoException("There is not enough task. "
                    + "\nPlease add more task or change another index.");
        }
        return this.taskList.get(index);
    }

    /**
     * Deletes Task at a given index in taskList
     *
     *
     * @throws EchoException If index is greater than the total number of tasks in taskList
     */
    public Task getTaskAndDelete(String taskDescription) throws EchoException {
        int index = Integer.parseInt(taskDescription) - 1;
        int largestIndex = this.sizeOfTaskList() - 1;

        if (index > largestIndex) {
            throw new EchoException("There is not enough task. "
                    + "\nPlease add more task or change another index.");
        }

        Task task = this.taskList.get(index);

        this.taskList.remove(index);
        Collections.rotate(this.taskList.subList(index, this.sizeOfTaskList()), -1);
        this.taskList.trimToSize();
        return task;
    }

    /**
     * Adds Task into taskList and print a display message
     *
     * @param userTask Task provided by user input
     */
    public void addTask(Task userTask) {
        this.taskList.add(userTask);
    }

    /**
     * Finds task in taskList with keyword in the description and return it as an ArrayList of task
     *
     * @param keyword word to search for in the list of task
     * @return an arrayList of all the task with description containing the keyword
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> resultArray = new ArrayList<>();
        for (Task task : this.taskList) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                resultArray.add(task);
            }
        }
        return resultArray;
    }
}
