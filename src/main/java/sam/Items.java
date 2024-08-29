package sam;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task list.
 */
public class Items {

    private List<Item> items;

    /**
     * Constructs an empty Items object.
     */
    public Items() {
        this.items = new ArrayList<>();
    }

    /**
     * Constructs an Items object with pre-loaded items from storage.
     *
     * @param items The list of items to be loaded.
     */
    public Items(List<Item> items) {
        this.items = items;
    }

    /**
     * Adds an item to the task list.
     *
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Deletes an item from the task list.
     *
     * @param index The index of the item to be deleted.
     */
    public void deleteItem(int index) {
        items.remove(index);
    }

    /**
     * Retrieves an item from the task list.
     *
     * @param index The index of the item to be retrieved.
     * @return The item at the specified index.
     */
    public Item getItem(int index) {
        return items.get(index);
    }

    /**
     * Gets the number of items in the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Retrieves the last added item from the task list.
     *
     * @return The last added item.
     */
    public Item getLastAdded() {
        return items.get(items.size() - 1);
    }

    /**
     * Gets all the items in the task list.
     *
     * @return The list of items.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Finds items that contain the specified keyword.
     *
     * @param keyword the keyword to search for
     * @return a list of items that contain the keyword
     */
    public List<Item> findItems(String keyword) {
        List<Item> matchingItems = new ArrayList<>();
        for (Item item : items) {
            if (item.toString().contains(keyword)) {
                matchingItems.add(item);
            }
        }
        return matchingItems;
    }

    /**
     * Returns a string representation of the Items object.
     *
     * @return The string representation of the Items object.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            output.append(i + 1).append(". ").append(items.get(i)).append("\n");
        }
        return output.toString();
    }
}