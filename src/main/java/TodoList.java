import java.util.ArrayList;

public class TodoList {
    private ArrayList<Task> todoList = new ArrayList<>();

    public TodoList() {

    }

    /**
     * Adds a new item to the todo list.
     * @param task A Task object to be added.
     * @return The update list of Todo items.
     */
    public ArrayList<Task> add(Task task) {
        todoList.add(task);
        return todoList;
    }

    /**
     * Get Task in TodoList
     * @param i The Index you want to retrieve
     * @return The Task at index i
     */
    public Task get(int i) {
        return todoList.get(i);
    }

    /**
     * Returns a string that consists of the number of tasks in the list.
     * @return A string that consists of the number of tasks in the list.
     */
    public String count() {
        return "Now you have " + this.todoList.size() + " tasks in the list.";
    }

    @Override
    public String toString() {
        String string = "Here are the tasks in your list:\n";
        int size = this.todoList.size();
        for (int i = 0; i < size; i++) {
            string += String.format("%d.%s", i + 1, todoList.get(i));
            if (i != size - 1) {
                string += "\n";
            }
        }
        return string;
    }

}
