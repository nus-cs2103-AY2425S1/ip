import java.util.ArrayList;

public class TodoList {
    private ArrayList<Task> tasks;

    public TodoList() {
        tasks = new ArrayList<Task>();
    }

    public void insert(String item) {
        tasks.add(new Task(item));
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getItems() {
        @SuppressWarnings("unchecked")
        ArrayList<Task> copy = (ArrayList<Task>) tasks.clone();
        return copy;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
            result += String.format("%d. %s",i + 1 , tasks.get(i)) + (i < tasks.size() - 1 ? "\n" : "");
        }

        return result;
    }
}
