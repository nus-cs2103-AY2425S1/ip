public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     * @param description
     */
    public ToDo(String description) {
        super(description.strip());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
