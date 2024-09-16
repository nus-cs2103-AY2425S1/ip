package espresso.task;
import java.util.ArrayList;
import espresso.command.InvalidCommandException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public void removeTask(int index) throws InvalidCommandException{
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            throw new InvalidCommandException("Invalid task index.");
        }
    }

    public Task getTask(int index) throws InvalidCommandException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new InvalidCommandException("Invalid task index.");
        }
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /* Finds all tasks that contain the specified substring.
     *
     * @param substring the substring to search for.
     * @return the list of tasks that contain the substring.
     */
    public TaskList find(String str) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.contains(str)) {
                matchingTasks.add(task);
            }
        }
        TaskList res = new TaskList(matchingTasks);
        return res;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res += i + 1 + "." + tasks.get(i);
            if (i < tasks.size() - 1) {
                res += "\n";
            }
        }
        return res;
    }
}