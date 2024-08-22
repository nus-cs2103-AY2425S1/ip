import java.util.List;
import java.util.ArrayList;

public class ToDoHandler {
    private List<String> tasks;

    public ToDoHandler() {
        this.tasks = new ArrayList<String>();
    }

    public void addToDo(String task) {
        this.tasks.add(task);
    }

    public String getTasksString() {
        String s = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            s += String.format("%d.", i + 1) +  this.tasks.get(i) + "\n";
        }
        return s.stripTrailing();
    }
}
