import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasklist;

    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    public void addTask(Task task) {
        this.tasklist.add(task);
    }

    public void removeTask(int index) {
        this.tasklist.remove(index);
    }

    public Task getTask(int index) {
        return this.tasklist.get(index);
    }

    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        Task currentTask;
        for (int i = 0; i < this.tasklist.size(); i++) {
            currentTask = this.tasklist.get(i);
            System.out.println((i + 1) + ". [" + currentTask.getEventType() +
                    "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
        }
    }
    public void printTask(Task task) {
        int size = tasklist.size();
        System.out.println("  [" + task.getEventType() + "][" + task.getStatusIcon() + "] " + task.description);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    public int size() {
        return this.tasklist.size();
    }
}
