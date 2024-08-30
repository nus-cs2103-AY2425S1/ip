package impl.storage;

import impl.interfaces.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /**
     * Handles all List processes by the user.
     * Works as a ArrayList under the hood.
     */
    private List<Task> list;
    public TaskList(){
        list = new ArrayList<>();
    }

    public void add(Task task){
        list.add(task);
    }

    public int size(){
        return list.size();
    }

    public Task get(int i){
        return list.get(i);
    }

    public void remove(int i){
        list.remove(i);
    }
    public void setLastDone() {
        list.get(list.size() - 1).setDone();
    }
    public String saveTasks(){
        StringBuilder lines = new StringBuilder();
        for(Task task: list){
            lines.append(task.loadString());
            lines.append(System.lineSeparator());
        }
        return String.valueOf(lines);
    }


}
