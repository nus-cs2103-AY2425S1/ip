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
        String mark = isDone() ? "X" : " ";
        return String.format("[T][%s] %s", mark, getDescription());
    }
}
