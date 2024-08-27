package echo.tasklist;

import echo.task.Task;
import echo.exception.EchoException;

import java.util.ArrayList;
import java.util.Collections;
public class TaskList {

    /** ArrayList of the tasks added to TaskList by the user*/
    private ArrayList<Task> taskList;
    /**
     * Constructor for TaskList
     */
    public TaskList(){
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

    public Task markAndGetTask(String taskDescription) {
        int index = Integer.parseInt(taskDescription) - 1;
        Task task = this.getTask(index);
        task.mark();
        return task;
    }

    public Task unmarkAndGetTask(String taskDescription) {
        int index = Integer.parseInt(taskDescription) - 1;
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
        if (index > largestIndex) {
            throw new EchoException("There is not enough task. " +
                    "\nPlease add more task or change another index.");
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
            throw new EchoException("There is not enough task. " +
                    "\nPlease add more task or change another index.");
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
}
