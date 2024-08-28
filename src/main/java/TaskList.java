import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public TaskList(Task[] taskArray) {
        this.list.addAll(Arrays.asList(taskArray));
    }

    public void printTasks(){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}