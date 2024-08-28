package lama;

import lama.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int i) {
        return taskList.remove(i);
    }

    /**
     * Finds and returns a list of tasks that contain the specified word.
     *
     * @param word The keyword to search for in the task description.
     * @return TaskList contain tasks that matched the word given.
     */
    public TaskList find(String word) {

        TaskList tasks = new TaskList();

        for (Task task: taskList) {
            if (task.toString().toLowerCase().contains(word.toLowerCase())) {
                tasks.add(task);
            }
        }

        return tasks;
    }


}
