package skywalker.task;
import skywalker.task.Task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> findTasks(String keyword){
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for(Task task:tasks){
            if(task.description.contains(keyword)){
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
