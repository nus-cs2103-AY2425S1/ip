import java.util.ArrayList;

/**
 * TodoList represents a list containing to-do entries
 */
public class TodoList {

    /** Array to hold the to-do items **/
    private final ArrayList<TodoItem> todoList;

    /**
     * Constructor method that initialise the FileStore and attempt to load to-do from local storage
     */
    public TodoList() {
        this(new ArrayList<>());
    }

    public TodoList(ArrayList<TodoItem> todoList) {
        this.todoList = todoList;
    }

    /**
     * Add an entry to the to-do list with provided to-do item
     *
     * @param item The to-dp item to add to the to-do list.
     */
    public boolean addItem(TodoItem item) {
        todoList.add(item);
        return true;
    }

    /**
     * Retrieve a cloned copy of the current to-do list
     *
     * @return a cloned copy of the current to-do list
     */
    public ArrayList<TodoItem> getTodoList() {
        return new ArrayList<>(todoList);
    }

    /**
     * Get the total number of to-do entries in the list
     *
     * @return The number of entries in the list
     */
    public int getTotal() {
        return this.todoList.size();
    }

    /**
     * Mark an item with the specified index as complete
     * @param index The index of the item
     */
    public void markComplete(int index) throws TohruException {
        if (index < 0 || index >= this.todoList.size()) {
            throw new TohruException("The entry you are looking to mark cannot be found");
        }
        this.todoList.get(index).setCompleted(true);
    }

    /**
     * Mark an item with the specified index as incomplete
     * @param index The index of the item
     */
    public void markIncomplete(int index) throws TohruException {
        if (index < 0 || index >= this.todoList.size()) {
            throw new TohruException("The entry you are looking to unmark cannot be found");
        }
        this.todoList.get(index).setCompleted(false);
    }

    /**
     * Delete the specified entry at the provided index
     *
     * @param index Index of the entry to be deleted
     * @return Status of the deletion
     */
    public boolean deleteItem(int index) {
        if (index < 0 || index >= this.todoList.size()) {
            return false;
        }
        this.todoList.remove(index);
        return true;
    }

    /**
     * Retrieve the status of an item in the list
     *
     * @return String representation of the item
     */
    public String getItemStatus(int index) {
        if (index < 0 || index >= this.todoList.size()) {
            return null;
        }
        return this.todoList.get(index).toString();
    }

}
