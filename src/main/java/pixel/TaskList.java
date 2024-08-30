package pixel;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTask(int index) {
        Task removedTask = getTask(index);
        tasks.remove(removedTask);
    }



    public void printTasks() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("\t" + i + "." + tasks.get(i-1));
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
