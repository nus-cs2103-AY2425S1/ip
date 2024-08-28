import java.util.List;

public class TaskList {
    private final ImList<Task> imTaskList;

    public TaskList() {
        imTaskList = new ImList<Task>();
    }

    private TaskList(ImList<Task> imTaskList) {
        this.imTaskList = imTaskList;
    }

    public TaskList addTask(Task task) {
        return new TaskList(imTaskList.add(task));
    }

    public TaskList deleteTask(int taskIndex) {
        return new TaskList(imTaskList.remove(taskIndex));
    }

    public TaskList markTaskAsDone(int taskIndex) {
        return taskIndex < imTaskList.size() && taskIndex >= 0
            ? new TaskList(imTaskList.set(
                    taskIndex, imTaskList.get(taskIndex).markAsDone()))
            : this;
    }

    public TaskList markTaskAsUndone(int taskIndex) {
        return taskIndex < imTaskList.size() && taskIndex >= 0
            ? new TaskList(imTaskList.set(
                    taskIndex, imTaskList.get(taskIndex).markAsUndone()))
            : this;
    }

    public Task get(int taskIndex) {
        return taskIndex < imTaskList.size() && taskIndex >= 0
            ? imTaskList.get(taskIndex)
            : new Task("");
    }

    public int size() {
        return imTaskList.size();
    }

    @Override
    public String toString() {
        String outputString;
        if (imTaskList.size() > 0) {
            outputString = "Here are your tasks in your list:\n";
            for (int i = 0; i < imTaskList.size(); i++) {
                outputString += (i+1) + "." + imTaskList.get(i) + "\n";
            }
        } else {
            outputString = "No tasks! What tasks would you like to add?\n";
        }
        return outputString;
    }

}
