import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) { // in case data/donna-tasks.txt exists
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskIdx) throws DonnaException {
        if (taskIdx >= 0 && taskIdx < tasks.size()) {
            return tasks.remove(taskIdx);
        } else {
            throw DonnaException.invalidTaskNumber();
        }
    }

    public Task markTask(int taskIdx, boolean isDone) throws DonnaException {
        if (taskIdx >= 0 && taskIdx < tasks.size()) {
            Task taskToMark = tasks.get(taskIdx);
            if (isDone) {
                taskToMark.markDone();
            } else {
                taskToMark.markNotDone();
            }
            return taskToMark;
        } else {
            throw DonnaException.invalidTaskNumber();
        }
    }

    public int getTaskCount() {
        return tasks.size();
    }
}
