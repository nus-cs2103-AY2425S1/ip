import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(){
        this.tasks = new ArrayList<Task>();

    }
    public void addTask(Task task){
        System.out.println("Task added");
    }
    public void removeTask(Task task){
        System.out.println("Task removed");
    }
    public void extend(ArrayList<Task> tasks){
        this.tasks.addAll(tasks);
    }
    public int size(){
        return this.tasks.size();
    }
    public Task get(int index){
        return this.tasks.get(index);
    }
    public void add(Task task){
        this.tasks.add(task);
    }
    public void remove(Task task){
        this.tasks.remove(task);
    }

}
