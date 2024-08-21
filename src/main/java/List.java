import java.util.ArrayList;

public class List {
    private final ArrayList<String> tasks = new ArrayList<>();

    /**
     * Adds a task to the list.
     * @param task String representation of the task to be added.
     */
    public void add(String task) {
        tasks.add(task);
    }

    /**
     * Returns a string representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return list.toString();
    }
}
