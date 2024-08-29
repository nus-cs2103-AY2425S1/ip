/**
 * Represents an item in a list.
 */
public class Item {

    private String name;
    private Boolean done;

    /**
     * Constructs a new Item object with the specified name.
     *
     * @param newname the name of the item
     */
    public Item(String newname) {
        this.name = newname;
        this.done = false;
    }

    /**
     * Marks the item as done.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Marks the item as undone.
     */
    public void markAsUndone() {
        this.done = false;
    }

    /**
     * Returns the status icon of the item.
     *
     * @return the status icon ("X" if done, " " if undone)
     */
    public String getStatusIcon() {
        return (done ? "X" : " ");
    }

    /**
     * Returns the item's data in a formatted string.
     *
     * @return the formatted string representing the item's data
     */
    public String toData() {
        String str;
        str = String.format("%s | %s", this.done ? "1" : "0", this.name);
        return str;
    }

    /**
     * Returns a string representation of the item.
     *
     * @return the string representation of the item
     */
    @Override
    public String toString() {
        String str;
        str = String.format("[%s] %s", this.getStatusIcon(), this.name);
        return str;
    }
}
