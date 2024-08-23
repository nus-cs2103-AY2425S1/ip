import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasklist;
    //private int numberOfTask;

    public TaskList() {
        this.tasklist = new ArrayList<>();
        //this.numberOfTask = 0;
    }

    public void addTask(Task task) {
        //this.tasklist[numberOfTask] = task;
        this.tasklist.add(task);
        //numberOfTask++;
        System.out.println("added: " + task + "\n"
                + "You have " + this.tasklist.size() + " tasks in the list");

    }

    public void deleteTask(int index) {
        System.out.println("deleted: " + this.tasklist.get(index - 1) + "\n");
        this.tasklist.remove(index - 1);
        System.out.println("You have " + this.tasklist.size() + " tasks in the list");
    }

    public void listTask() {
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasklist.get(i));
        }
    }

    public void markTask(int index) {
        this.tasklist.get(index - 1).complete();
        System.out.println("Marked as done:\n" + this.tasklist.get(index - 1));
    }

    public void unmarkTask(int index) {
        this.tasklist.get(index - 1).uncomplete();
        System.out.println("Marked as undone:\n" + this.tasklist.get(index - 1));
    }
}