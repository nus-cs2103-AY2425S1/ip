import java.util.ArrayList;

public class TaskList {
    ArrayList<String> items;

    public TaskList() {
        items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    public String getList() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            list.append(i + 1).append(". ").append(items.get(i)).append("\n");
        }
        return list.toString();
    }
}
