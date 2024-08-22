import java.util.ArrayList;

public class ToDo {
    private ArrayList<String> todoList = new ArrayList<>();

    public void add(String task) {
        todoList.add(task);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < todoList.size(); i++) {
            str = str + (i + 1) + ". " + todoList.get(i) + "\n";
        }
        return str;
    }
}
