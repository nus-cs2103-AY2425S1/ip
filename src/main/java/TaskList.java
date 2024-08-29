import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks = null;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getSpecificTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void listTasks() {
        System.out.println("My Task List");
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet :o");
        } else {
            int label = 1;
            for (Task task : tasks) {
                System.out.println(label + ". " + task.toString());
                label++;
            }
        }
        System.out.println();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public void markTaskAsDone(int index) {
        Task task = this.tasks.get(index);
        task.markAsDone();
    }

    public void markTaskAsUndone(int index) {
        Task task = this.tasks.get(index);
        task.unmarkAsUndone();
    }

}
