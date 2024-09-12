package Bellroy.task;

import Bellroy.task.Task;

import java.util.ArrayList;
import java.util.List;

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

    public TaskList findTask(String keyword) {
        TaskList matchingTask = new TaskList();
        for (Task task: taskList) {
            if (task.description.contains(keyword)) {
                matchingTask.addTask(task);
            }
        }
        return matchingTask;
    }

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
