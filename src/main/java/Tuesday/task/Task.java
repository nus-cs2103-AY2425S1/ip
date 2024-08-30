package Tuesday.task;

import Tuesday.util.Storage;

import java.util.ArrayList;
import java.io.File;

public class Task {
    private String description;
    private boolean isMarked;
    public static ArrayList<Task> taskArrayList = new ArrayList<>();
    public static int count = 0;

    public Task(String description) {
        if (description.isEmpty()){
            return;
        }
        this.description = description;
        this.isMarked = false;
        Task.taskArrayList.add(this);
        count++;
    }

    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
        Task.taskArrayList.add(this);
        count++;
    }

    public Task(Storage storage) {
        storage.createNewDatafile();
    }

    public String getDoneX() {
        if (this.isMarked)
            return "X";
        else
            return " ";
    }

    public String getDone1() {
        if (this.isMarked)
            return "1";
        else
            return "0";
    }

    public void changeDone(boolean state) {
        this.isMarked = state;
    }

    public static void deleteTask(int index) {
        count--;
        Task.taskArrayList.remove(index);
    }
    public String writeToDatafile(File dataFile){
        return this.description;
    }


    @Override
    public String toString() {
        return "["+ this.getDoneX() +"] "+ this.description;
    }
}
