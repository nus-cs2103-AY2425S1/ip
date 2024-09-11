package luffy;

import java.util.ArrayList;

/**
 * Represents an array of Task for the Storage system to access and manipulate
 */
@SuppressWarnings("checkstyle:Regexp")
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> existingTasks) {
        this.taskList = existingTasks;
    }

    /**
     * This method adds a task into the existing taskList array
     *
     * @param task the task to be added into the array
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * This method removes a task from the existing taskList array
     *
     * @param index the position of task to be removed
     */
    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * This method retrieves a task from the existing taskList array
     *
     * @param index the position of task to be retrieved
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the updated arraylist of all current tasks by the user
     *
     * @return array list of all tasks
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Returns the size of the task array
     *
     * @return size of task array
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * diuew
     * @param keyword
     * @return
     */
    public TaskList findTasks(String keyword) {
        TaskList tempList = new TaskList();
        for (Task existingTask : taskList) {
            String taskInfo = existingTask.getTaskInfo();
            if (taskInfo.contains(keyword)) {
                tempList.addTask(existingTask);
            }
        }
        return tempList;
    }
}
