import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList = new ArrayList<>();

    public TaskList() {

    }

    public void addTask(String taskName) {
        taskList.add(new Task(taskName));
    }

    public Task findTask(String input) {

        int taskNo = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;

        return taskList.get(taskNo);
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder("Here are your tasks, champ. Let's see how many you can actually cross off.\n");
        int index = 1;

        for (Task task : taskList) {
            str.append(index).append(". ").append(task.toString()).append('\n');
            index++;
        }

        if (!str.isEmpty()) {
            str.setLength(str.length() - 1);
        }
        return str.toString();

    }
}
