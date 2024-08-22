import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(String description) {
        Task newTask = new Task(description);
        this.taskList.add(newTask);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Here are the tasks in your list. Otto would rather be asleep than dealing with all this.");
        int idx = 1;
        for (Task task : taskList) {
            res.append(String.format("\n\t%d. %s", idx++, task.toString()));
        }
        return res.toString();
    }
}
