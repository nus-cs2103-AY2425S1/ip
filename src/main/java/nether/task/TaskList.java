package nether.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints out the task list along with its status (done or not done)
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public TaskList searchTask(String searchString) {
        List<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < getSize(); i++) {
            if (this.getTask(i).getDescription().contains(searchString)) {
                matchingTasks.add(tasks.get(i));
            }
        }

        return new TaskList(matchingTasks);
    }

}
