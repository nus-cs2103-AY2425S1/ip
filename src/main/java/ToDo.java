public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     * @param description a String describing the ToDo
     */
    public ToDo(String description) {
        super(description.strip());
    }

    /**
     * Returns a formatted String representing the ToDo object and its fields
     * @return a String representation of the ToDo object
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
