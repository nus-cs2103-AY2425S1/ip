import java.util.List;

public class TaskList {
    private final ImList<Task> taskList;

    public TaskList() {
        taskList = new ImList<Task>();
    }

    private TaskList(ImList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList addTask(Task task) {
        return new TaskList(taskList.add(task));
    }

    public TaskList deleteTask(int taskIndex) {
        return new TaskList(taskList.remove(taskIndex));
    }

    public TaskList markTaskAsDone(int taskIndex) {
        return taskIndex < taskList.size() && taskIndex >= 0
            ? new TaskList(taskList
                .set(taskIndex, taskList
                    .get(taskIndex)
                    .markAsDone()))
            : this;
    }

    public TaskList markTaskAsUndone(int taskIndex) {
        return taskIndex < taskList.size() && taskIndex >= 0
            ? new TaskList(taskList
                .set(taskIndex, taskList
                    .get(taskIndex)
                    .markAsUndone()))
            : this;
    }

    public Task get(int taskIndex) {
        return taskIndex < taskList.size() && taskIndex >= 0
            ? taskList.get(taskIndex)
            : new Task("");
    }

    public int size() {
        return taskList.size();
    }

    @Override
    public String toString() {
        String outputString;
        if (taskList.size() > 0) {
            outputString = "Here are your tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                outputString += (i+1) + "." + taskList.get(i) + "\n";
            }
        } else {
            outputString = "No tasks! What tasks would you like to add?\n";
        }
        return outputString;
    }

}
