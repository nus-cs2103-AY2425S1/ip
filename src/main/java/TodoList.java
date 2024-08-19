/**
 * TodoList represents a list containing to-do entries
 */
public class TodoList {

    /** Array to hold the to-do items **/
    private final TodoItem[] todoList = new TodoItem[100];
    private int index = 0;

    /**
     * Add an entry to the to-do list with provided to-do item
     *
     * @param item The to-dp item to add to the to-do list.
     */
    public boolean addItem(TodoItem item) {
        if (index == todoList.length) {
            return false;
        }

        todoList[index] = item;
        index++;
        return true;
    }

    /**
     * List out all the items in the to-do list
     *
     * @return return an array of stings representing each entry in the to-do list
     */
    public String[] listItems() {
        String[] items = new String[index];
        for (int i = 0; i < index; i++) {
            items[i] = String.format("%d. %s", i + 1, todoList[i].toString());
        }
        return items;
    }

    /**
     * Mark an item with the specified index as complete
     * @param index The index of the item
     * @return Status of the operation
     */
    public boolean markComplete(int index) {
        if (index < 0 || index >= this.index) {
            return false;
        }
        this.todoList[index].setCompleted(true);
        return true;
    }

    /**
     * Mark an item with the specified index as incomplete
     * @param index The index of the item
     * @return Status of the operation
     */
    public boolean markIncomplete(int index) {
        if (index < 0 || index >= this.index) {
            return false;
        }
        this.todoList[index].setCompleted(false);
        return true;
    }

    /**
     * Retrieve the status of an item in the list
     *
     * @return String representation of the item
     */
    public String getItemStatus(int index) {
        if (index < 0 || index >= this.index) {
            return null;
        }
        return this.todoList[index].toString();
    }


}
