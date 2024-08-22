import TaskType.Task;
import TaskType.TaskBuilder;
import java.util.ArrayList;

public class ListManager {
    private ArrayList<Task> itemList = new ArrayList<>();

    public void createItem(TaskBuilder builder) {
        itemList.add(builder.build());
    }

    public String listItems() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < itemList.size(); i++) {
            Task task = itemList.get(i);
            result.append(i + 1).append(". ").append(task.toString()).append("\n");
        }
        return result.toString();
    }

    public void setDone(boolean done, int index) {
        if (index > 0 && index <= itemList.size()) {
            itemList.get(index - 1).setDone(done);
        }
    }

    public void delete(int index) {
        if (index > 0 && index <= itemList.size()) {
            itemList.remove(index-1);
        }
    }

    public String getItem(int index) {
        if (index > 0 && index <= itemList.size()) {
            Task task = itemList.get(index - 1);
            return task.toString();
        }
        return "";
    }


}
