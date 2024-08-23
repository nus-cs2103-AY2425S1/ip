import java.util.ArrayList;

public class TaskStorage {
    private ArrayList<Task> tasks;
    private int count;

    public TaskStorage(){
        this.tasks = new ArrayList<>();
        this.count = 0;
    }

    public void addTask(Task task){
        this.tasks.add(task);
        System.out.println("\tOk can. I've added this task:");
        System.out.println("\t  " + task);
        this.count++;
        System.out.println("\tNow you have " + this.count + " tasks in the list.");
    }

    public void deleteTask(int i) throws InvalidTaskException {
        if (i < 0 || i >= this.count) {
            throw new InvalidTaskException("Don't bother, task non-existent");
        }
        Task task = this.tasks.get(i);
        this.tasks.remove(i);
        System.out.println("\tSigh. I've removed this task:");
        System.out.println("\t  " + task);
        this.count--;
        System.out.println("\tNow you have " + this.count + " tasks in the list.");
    }

    public void markTask(int i) throws InvalidTaskException {
        if (i < 0 || i >= this.count) {
            throw new InvalidTaskException("Don't bother, task non-existent");
        }
        Task task = this.tasks.get(i);
        task.markDone();
        System.out.println("\t0.o I've marked this as done: ");
        System.out.println("\t  " + task);
    }

    public void unmarkTask(int i) throws InvalidTaskException {
        if (i < 0 || i >= this.count) {
            throw new InvalidTaskException("Don't bother, task non-existent");
        }
        Task task = this.tasks.get(i);
        task.markNotDone();
        System.out.println("\tAiyo, the task is marked as not done: ");
        System.out.println("\t  " + task);
    }

    public void listAllTasks() {
        System.out.println("\tSo many things to do...");
        for (int i = 0; i < count; i++) {
            int index = i + 1;
            Task task = tasks.get(i);
            System.out.println("\t" + index + ". " + task);
        }
    }
}
