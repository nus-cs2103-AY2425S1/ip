/**
 * Represents a to-do item.
 * Inherits from the Item class.
 */
public class ToDo extends Item {

    /**
     * Constructs a new ToDo object with the given name.
     *
     * @param newname The name of the to-do item.
     */
    public ToDo(String newname) {
        super(newname);
    }

    /**
     * Converts the to-do item to a formatted string for data storage.
     *
     * @return The formatted string representation of the to-do item.
     */
    @Override
    public String toData() {
        String str = String.format("T | %s\n", super.toData());
        return str;
    }

    /**
     * Converts the to-do item to a string representation.
     *
     * @return The string representation of the to-do item.
     */
    @Override
    public String toString() {
        String str = String.format("[T] %s", super.toString());
        return str;
    }
    
}
