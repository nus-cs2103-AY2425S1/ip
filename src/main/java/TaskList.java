import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Integer size(){
        return this.tasks.size();
    }

    public void add(Task task){
        this.tasks.add(task);
    }

    public Task get(int index){
        return this.tasks.get(index);
    }

    public void remove(int index){
        this.tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
