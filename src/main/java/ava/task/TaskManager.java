package ava.task;

import ava.files.FileManager;
import ava.task.Tasks.Deadline;
import ava.task.Tasks.Event;
import ava.task.Tasks.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;
    private final FileManager fileManager;
    public TaskManager() {
        this.tasks = new ArrayList<Task>(){
            @Override
            public String toString() {
                StringBuilder out = new StringBuilder();
                for(int i = 1; i <=size();i++){
                    out.append(i);
                    out.append(". ");
                    out.append(get(i - 1));
                    out.append("\n");
                }
                return out.toString();
            }
        };

        this.fileManager = new FileManager();
        this.tasks = fileManager.getTasks();
    }

    public List<Task> getTasks() {
        // in the future return a view of the list
        // rather than the list to avoid errors
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addTask(String task) {
        tasks.add(new Todo(task));
    }

    public void addTask(String task,String time){
        tasks.add(new Deadline(task, time));
    }

    public void addTask(String task,String startTime,String endTime){
        tasks.add(new Event(task, startTime, endTime));
    }

    public void removeTask(int taskId) {
        if(taskId > 0 && taskId <= tasks.size()) {
            tasks.remove(taskId - 1);
        } else {
            throw new IllegalArgumentException("Trying to delete nonexistent task");
        }
    }
}
