import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();

    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean addTask(Task task) {
        if (tasks.add(task)) {
            return true;
        }
        return false;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public boolean markTask(int index) {
        return tasks.get(index).completeTask();
    }

    public boolean unmarkTask(int index) {
        return tasks.get(index).uncompleteTask();
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public int taskCount() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res = res + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return res;
    }
}
