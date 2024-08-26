package main.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> lst = new ArrayList<>();
    private Storage storage = new Storage();

    /**
     * Save tasks to file using Storage.
     */
    public void saveTasksToFile() {
        storage.save(lst);
    }

    /**
     * Loads tasks from saved file using Storage.
     */
    public void loadTasksFromFile() {
        lst = storage.load();
    }

    public ArrayList<Task> getTaskList() {
        return lst;
    }

    public void addToList(Task task){
        try {
            lst.add(task);
            saveTasksToFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showList() {
        System.out.println("Here are the tasks in your list:");
        int num = 1;
        for (int i = 0; i < lst.size(); ++i) {
            System.out.println(num + ". " + lst.get(i).getDesc());
            num += 1;
        }
    }

    public Task delete(int i) {
        Task task = lst.remove(i - 1);
        saveTasksToFile();
        return task;
    }
}
