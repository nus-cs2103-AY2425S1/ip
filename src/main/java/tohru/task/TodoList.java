package tohru.task;

import java.util.ArrayList;
import java.util.List;

import tohru.exception.TohruException;

/**
 * Represents a list containing to-do entries.
 */
public class TodoList {

    /** Array to hold the to-do items. */
    private final ArrayList<TodoItem> todoList;

    /**
     * Initialises with a provided list.
     *
     * @param todoList List to be used as the to-do list.
     */
    public TodoList(ArrayList<TodoItem> todoList) {
        assert todoList != null : "Todo list should not be null";

        this.todoList = todoList;
    }

    /**
     * Initialises with an empty list.
     */
    public TodoList() {
        this(new ArrayList<>());
    }

    /**
     * Adds an entry to the to-do list with provided to-do item.
     *
     * @param item The to-do item to add to the to-do list.
     */
    public void addItem(TodoItem item) {
        todoList.add(item);
    }

    /**
     * Retrieves a cloned copy of the current to-do list.
     *
     * @return A cloned copy of the current to-do list.
     */
    public ArrayList<TodoItem> getTodoList() {
        return new ArrayList<>(todoList);
    }

    /**
     * Gets the total number of to-do entries in the list.
     *
     * @return The number of entries in the list.
     */
    public int getTotal() {
        return this.todoList.size();
    }

    /**
     * Marks an item with the specified index as complete.
     *
     * @param index The index of the item.
     * @throws TohruException When the index is out of bound for the to-do list.
     */
    public void markComplete(int index) throws TohruException {
        if (index < 0 || index >= this.todoList.size()) {
            throw new TohruException("The entry you are looking to mark cannot be found");
        }
        this.todoList.get(index).setCompleted(true);
    }

    /**
     * Marks an item with the specified index as incomplete.
     *
     * @param index The index of the item.
     * @throws TohruException When the index is out of bound for the to-do list.
     */
    public void markIncomplete(int index) throws TohruException {
        if (index < 0 || index >= this.todoList.size()) {
            throw new TohruException("The entry you are looking to unmark cannot be found");
        }
        this.todoList.get(index).setCompleted(false);
    }

    /**
     * Deletes the specified entry at the provided index.
     *
     * @param index Index of the entry to be deleted.
     * @throws TohruException When the index is out of bound for the to-do list.
     */
    public void deleteItem(int index) throws TohruException {
        if (index < 0 || index >= this.todoList.size()) {
            throw new TohruException("The entry you are looking to delete cannot be found");
        }
        this.todoList.remove(index);
    }

    /**
     * Retrieves the status of an item in the list.
     *
     * @return String representation of the item.
     */
    public String getItemStatus(int index) {
        if (index < 0 || index >= this.todoList.size()) {
            return null;
        }
        return this.todoList.get(index).toString();
    }

    /**
     * Searches the to-do list for provided keyword.
     *
     * @param keyword Keyword used for filtering.
     * @return An ArrayList with entries that matches the keyword.
     */
    public ArrayList<TodoItem> filter(String keyword) {
        ArrayList<TodoItem> clone = new ArrayList<>(todoList);
        List<TodoItem> filteredList = clone.stream().filter((item) -> item.contains(keyword)).toList();
        return new ArrayList<>(filteredList);
    }
}
