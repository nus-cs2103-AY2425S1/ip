import java.util.ArrayList;

public class TodoList {
    private ArrayList<Todo> todoList = new ArrayList<>();

    public TodoList() {

    }

    /**
     * Adds a new item to the todo list.
     * @param todo The description of the todo item to be added.
     * @return The update list of Todo items.
     */
    public ArrayList<Todo> add(String todo) {
        todoList.add(new Todo(todo));
        return todoList;
    }

    @Override
    public String toString() {
        String string = "";
        int size = this.todoList.size();
        for (int i = 0; i < size; i++) {
            string += String.format("%d. %s", i + 1, todoList.get(i));
            if (i != size - 1) {
                string += "\n";
            }
        }
        return string;
    }

}
