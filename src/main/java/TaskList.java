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
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNumber) throws AgaveException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removed = tasks.remove(taskNumber - 1);
            System.out.println("Removed: " + removed);
            showNumberOfTasks();
        } else {
            throw new AgaveException("Task number is out of range. Enter a valid task number.");
        }
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    public void markTask(int taskNumber) throws AgaveException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).markAsDone();
        } else {
            throw new AgaveException("Task number is out of range. Please enter a valid task number.");
        }
    }

    public void unmarkTask(int taskNumber) throws AgaveException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).unmarkAsDone();
        } else {
            throw new AgaveException("Task number is out of range. Please enter a valid task number.");
        }
    }

    public void showNumberOfTasks() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}