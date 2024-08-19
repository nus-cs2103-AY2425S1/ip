import java.util.ArrayList;

/**
 * TodoList represents a list containing to-do entries
 */
public class TodoList {

    /** Array to hold the to-do items **/
    private final ArrayList<TodoItem> todoList = new ArrayList<TodoItem>();

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
     * List out all the items in the to-do list
     *
     * @return return an array of stings representing each entry in the to-do list
     */
    public String[] listItems() {
        String[] items = new String[todoList.size()];
        for (int i = 0; i < todoList.size(); i++) {
            items[i] = String.format("%d. %s", i + 1, todoList.get(i).toString());
        }
        return items;
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
     * @return Status of the operation
     */
    public boolean markComplete(int index) {
        if (index < 0 || index >= this.todoList.size()) {
            return false;
        }
        this.todoList.get(index).setCompleted(true);
        return true;
    }

    /**
     * Mark an item with the specified index as incomplete
     * @param index The index of the item
     * @return Status of the operation
     */
    public boolean markIncomplete(int index) {
        if (index < 0 || index >= this.todoList.size()) {
            return false;
        }
        this.todoList.get(index).setCompleted(false);
        return true;
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
