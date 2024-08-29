import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public String list() {
        StringBuilder listSummary = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            listSummary.append((i + 1)).append(". ")
                    .append(taskList.get(i).toString()).append("\n");

        }
        return listSummary.toString();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }

    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public String delete(int taskId) throws GarfieldException {
        Task task = this.get(taskId);
        taskList.remove(task);
        return task.toString();
    }

    public String mark(int taskId) throws GarfieldException {
        Task task = this.get(taskId);
        task.markAsDone();
        return task.toString();
    }

    public String unmark(int taskId) throws GarfieldException {
        Task task = this.get(taskId);
        task.markAsUndone();
        return task.toString();
    }

    private Task get(int taskId) throws GarfieldException {
        if (taskId > taskList.size()) {
            throw new GarfieldException("The task doesn't exist!");
        }

        return taskList.get(taskId - 1);
    }
}
