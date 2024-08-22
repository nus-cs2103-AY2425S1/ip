import java.util.ArrayList;

public class HimList {
    private ArrayList<Task> list;

    public HimList() {
        this.list = new ArrayList<>();
    }

    public void add(String item) {
        list.add(new Task(item));
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            output.append(String.format("%d. %s\n", i + 1, list.get(i)));
        }
        return output.toString();
    }
}
