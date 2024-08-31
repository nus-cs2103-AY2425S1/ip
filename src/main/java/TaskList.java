import java.util.ArrayList;

public class TaskList {
    protected final ArrayList<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public Task getTask(int index) {
        return this.tasks.get(index);
    }
    
    public int getCount() {
        return this.tasks.size();
    }
    
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    
    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }
}
