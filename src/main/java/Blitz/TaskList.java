package blitz;

/* My import */
import task.Task;

/* System import */
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public int size() {
        return this.list.size();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public Task deleteTask(int i) {
        return this.list.remove(i);
    }

    public Task getTask(int i) {
        return this.list.get(i);
    }

    public ArrayList<Task> getAllTask() {
        return this.list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskList t = (TaskList) o;

        if (this.list.size() != t.list.size()) {
            return false;
        }

        for (int i = 0; i < this.list.size(); i++) {
            if (!this.list.get(i).equals(t.list.get(i))) {
                return false;
            }
        }

        return true;
    }
}
