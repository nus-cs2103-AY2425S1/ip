package echoa;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * The method takes in an index (0-indexed) and
     * returns the Task at that particular index.
     *
     * @param index Index is a number reference (0-indexed) in the list of task.
     * @return Task is the task at the particular index in the taskList.
     */
    public Task getSpecificTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * The method returns the number of tasks in the taskList.
     *
     * @return number of tasks in the taskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * The method adds the inputted task into the taskList.
     *
     * @param task task to be added to taskList.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * The method deletes the task at the particular index in the list.
     *
     * @param index index of task to be deleted.
     * @throws ListOutOfBoundsException when the index is less than 0 or more than or equal to the size of the taskList.
     */
    public void deleteTask(int index) throws ListOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new ListOutOfBoundsException(index);
        }
        this.taskList.remove(index);
    }

    /**
     * The method marks the task at the particular index in the list as done.
     *
     * @param index index of the task to be marked as done.
     * @throws ListOutOfBoundsException when the index is less than 0 or more than or equal to the size of the taskList.
     */
    public void markTaskAsDone(int index) throws ListOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new ListOutOfBoundsException(index);
        }
        Task task = this.taskList.get(index);
        task.markAsDone();
    }

    /**
     * The  method marks the tasks at the particular index in the list and undone.
     *
     * @param index index of the task to be marked as undone.
     * @throws ListOutOfBoundsException when the index is less than 0 or more than or equal to the size of the taskList.
     */
    public void markTaskAsUndone(int index) throws ListOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new ListOutOfBoundsException(index);
        }
        Task task = this.taskList.get(index);
        task.unmarkAsUndone();
    }
}
