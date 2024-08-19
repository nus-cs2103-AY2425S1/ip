package tasklist;
import tasklist.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    private enum TaskType {
        ToDos, Events, Deadlines
    }
    
    public TaskList() {
        list = new ArrayList<>();
    }

    public String process(String input) {
        list.add(new Task(input));
        return "    added: " + input;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += "    " + (i + 1) + ". " + list.get(i) + "\n";
        }
        return output.stripTrailing();
    }
}