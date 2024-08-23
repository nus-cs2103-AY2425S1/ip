package matcha.tasklist;
import java.util.ArrayList;
import matcha.task.Task;

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
        if (tasks.size() <= 0) {
            System.out.println("You have no tasks in the list.");
            return;
        }
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

    /**
     * Finds tasks that contain the given keyword and adds it to an ArrayList.
     *
     * @param keyword The keyword to search for.
     * @return An ArrayList of tasks that contain the keyword.
     */
    public ArrayList<Task> findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
