package rasputin.task;

/**
 * Represents a task that has no deadline to be completed by.
 *
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "Todo";
    }

    /**
     * Returns the string as the format to be saved in the .txt file.
     *
     * @return String in the format to be saved
     */
    @Override
    public String toSaveFormat() {
        String str = String.format("T|%s|%s\n", getStatusIdentifier(), description);
        return str;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
