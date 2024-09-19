package bellroy.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Encapsulates the list of tasks in the chatbot
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * If there is no existing TaskList, create a new list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * If there exists a TaskList, take in that list and proceed to add and delete accordingly
     * @param taskList
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     *
     * @return number of tasks in the TaskList
     */
    public int size() {
        return taskList.size();
    }

    /**
     *
     * @param index specific task in the TaskList
     * @return the task at index
     */
    public Task get(int index) {
        assert index >= 0: "index cannot be negative";
        return taskList.get(index);
    }

    /**
     * adds a task to the TaskList
     * @param task to add to the TaskList
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * removes a task from the tasklist
     * @param index of the task to be deleted
     */
    public void removeTask(int index) {
        assert index >= 0: "index cannot be negative";
        taskList.remove(index);
    }

    /**
     *
     * @return the list of Tasks
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * searches for tasks in the tasklist that contains the matching keyword
     * @param keyword the keyword to search for in the tasks
     * @return the list of tasks that contain the matching keyword
     */
    public TaskList findTask(String keyword) {
        TaskList matchingTask = new TaskList();
        for (Task task: taskList) {
            if (task.description.contains(keyword)) {
                matchingTask.addTask(task);
            }
        }
        return matchingTask;
    }

    /**
     * searches the tasks in the tasklist that matches the association
     * @param keyword the association to be filtered by
     * @return the tasks with the association
     */
    public TaskList filterAssociation(String keyword) {
        TaskList filteredTask = new TaskList();
        for (Task task: taskList) {
            if (Objects.equals(task.getAssociation(), keyword)) {
                filteredTask.addTask(task);
            }
        }
        return filteredTask;
    }

    /**
     * checks if the tasklist is empty
     * @return the boolean of whether the tasklist is empty
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < taskList.size(); i++) {
            s += String.format("%d.%s", i+ 1, taskList.get(i));
            if (i < taskList.size() - 1) {
                s += "\n";
            }
        }
        return s;
    }

}
