import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int taskNum) {
        this.tasks.remove(taskNum);
    }

    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void listTasks() {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            String task = (i + 1) + ". " + tasks.get(i);
            System.out.println("\t" + task);
        }
    }

    public void printTask(Task task) {
        System.out.println(task);
        System.out.println("You have " + tasks.size() + " tasks in the list.");
    }



}
