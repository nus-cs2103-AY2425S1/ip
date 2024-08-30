package Tuesday.task;

import Tuesday.util.Storage;

import java.util.ArrayList;
import java.io.File;

public class Task {
    private String description;
    private boolean done;
    public static ArrayList<Task> taskArrayList = new ArrayList<>();
    public static int count = 0;

    public Task(String description) {
        if (description.isEmpty()){
            return;
        }
        this.description = description;
        this.done = false;
        Task.taskArrayList.add(this);
        count++;
    }

    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
        Task.taskArrayList.add(this);
        count++;
    }

    public Task(Storage storage) {
        storage.create_new_datafile();
    }

    public String getDoneX() {
        if (this.done)
            return "X";
        else
            return " ";
    }

    public String getDone1() {
        if (this.done)
            return "1";
        else
            return "0";
    }

    public void changeDone(boolean state) {
        this.done = state;
    }

    public static void deleteTask(int index) {
        count--;
        Task.taskArrayList.remove(index);
    }

    public String getDescription() {
        return this.description;
    }
    public String write_to_datafile(File dataFile){
        return this.description;
    }


    @Override
    public String toString() {
        return "["+ this.getDoneX() +"] "+ this.description;
    }
}
