import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {return tasks;}

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) throws OllieException {
        if (index < 0 || index >= tasks.size()) {
            throw new OllieException("Invalid Serial Number!");
        }
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        return task;
    }

    public Task markAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task markAsUndone(int index) {
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    @Override
    public String toString() {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++) {
            list.add(String.format("%d.%s%s", i + 1,tasks.get(i),i == tasks.size() - 1 ? "": "\n"));
        }
        return String.join("",list);
    }
}
