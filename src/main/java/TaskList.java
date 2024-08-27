import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> checklist) {
        this.tasks = checklist;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task markTaskAsDone(int index) {
        Task doneTask = this.tasks.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    public Task markTaskAsUndone(int index) {
        Task undoneTask = this.tasks.get(index);
        undoneTask.markAsDone();
        return undoneTask;
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
