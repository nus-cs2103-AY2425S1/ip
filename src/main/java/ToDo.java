public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representation
     *
     * @return data format
     */
    @Override
    public String toDataFormat() {
        return "todo | " + super.toDataFormat();
    }

    /**
     * Returns a formatted message with status icon
     * and description
     *
     * @return display message
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
