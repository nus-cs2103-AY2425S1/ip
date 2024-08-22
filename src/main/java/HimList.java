import java.util.ArrayList;

public class HimList {
    private ArrayList<String> list;

    public HimList() {
        list = new ArrayList<>();
    }

    public void add(String item) {
        list.add(item);
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            output.append(String.format("%d. %s\n", i + 1, list.get(i)));
        }
        return output.toString();
    }
}
