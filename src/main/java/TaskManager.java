import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    // String type can be changed to Tasks later on
    private List<String> tasks;

    public TaskManager() {
        tasks = new ArrayList<>(100);
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void listTasks() {
        String taskString = "";
        for (int i=0; i<tasks.size(); i++) {
            if (i != tasks.size() - 1) {
                taskString += i + 1 + ". " + tasks.get(i) + "\n    ";
            }
            else {
                taskString += i + 1 + ". " + tasks.get(i);
            }
        }
        printLines(taskString);
    }

    public void printLines(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + input);
        System.out.println("    ____________________________________________________________\n");
    }
}
