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
    private ArrayList<Task> taskList;
    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int sizeOfTaskList() {
        return taskList.size();
    }

    /**
     * Marks task with given value and returns the task that is marked
     *
     * @param value index of the task to be marked in the taskList.
     * @return the task that is being marked.
     */
    public Task markAndGetTask(String value) {
        int index = Integer.parseInt(value) - 1;
        Task task = getTask(index);
        task.mark();
        return task;
    }

    /**
     * Unmarks task with given value and returns the task that is unmarked
     *
     * @param value index of the task to be unmarked in the taskList.
     * @return the task that is being unmarked.
     */
    public Task unmarkAndGetTask(String value) {
        int index = Integer.parseInt(value) - 1;
        Task task = getTask(index);
        task.unmark();
        return task;
    }

    /**
     * Returns the Task at a given index of taskList
     *
     * @param index of the task in taskList
     * @return task that is at the index
     * @throws EchoException If index is greater than the total number of tasks in taskList
     */
    public Task getTask(int index) {
        int largestIndex = sizeOfTaskList() - 1;
        if (index > largestIndex) {
            throw new EchoException("There is not enough task. "
                    + "\nPlease add more task or change another index.");
        }
        return taskList.get(index);
    }

    /**
     * Deletes Task at a given index in taskList
     *
     * @return task that is deleted
     * @throws EchoException If index is greater than the total number of tasks in taskList
     */
    public Task getTaskAndDelete(String taskDescription) throws EchoException {
        int index = Integer.parseInt(taskDescription) - 1;
        int largestIndex = sizeOfTaskList() - 1;

        if (index > largestIndex) {
            throw new EchoException("There is not enough task. "
                    + "\nPlease add more task or change another index.");
        }

        Task task = taskList.get(index);

        taskList.remove(index);
        Collections.rotate(taskList.subList(index, sizeOfTaskList()), -1);
        taskList.trimToSize();
        return task;
    }

    public void addTask(Task userTask) {
        taskList.add(userTask);
    }

    /**
     * Finds task in taskList with keyword in the description and return it as an ArrayList of task
     *
     * @param keyword word to search for in the list of task
     * @return an arrayList of all the task with description containing the keyword
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> resultArray = new ArrayList<>();
        for (Task task : taskList) {
            if (task.isMatch(keyword)){
                resultArray.add(task);
            }
        }
        return resultArray;
    }
}
