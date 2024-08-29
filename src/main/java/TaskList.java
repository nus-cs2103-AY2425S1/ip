import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i).getStatus());
        }
    }

    public Task markAsDone(int index) {
        Task task = this.tasks.get(index - 1);
        task.setDone();
        return task;
    }

    public Task markAsUndone(int index) {
        Task task = this.tasks.get(index - 1);
        task.setUndo();
        return task;
    }

    public int getSize() {
        return tasks.size();
    }

    public void write(FileWriter fw) throws IOException {
        for (Task t: this.tasks) {
            fw.write(t.toFileRecord() + "\n");
        }
    }
}
