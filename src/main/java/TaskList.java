import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public TaskList(Task[] taskArray) {
        this.list.addAll(Arrays.asList(taskArray));
    }

    public int getLength() {
        return this.list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public void add(Task newTask) {
        list.add(newTask);
    }

    public void delete(int position) {
        list.remove(position - 1);
    }

    public String showTasks(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i));
            result.append("\n");
        }
        return result.toString();
    }


}