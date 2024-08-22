import java.util.ArrayList;

public class Store {
    ArrayList<String> items;

    public Store() {
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
