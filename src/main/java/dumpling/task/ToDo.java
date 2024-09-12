package dumpling.task;

/**
 * ToDo class that represents todo tasks
 */
public class ToDo extends Task {

    /**
     * Constructor for todo class
     * @param description Todo description
     */
    public ToDo(String description) {
        this(description, "");
    }

    /**
     * Constructor for todo class
     * @param description Todo description
     * @param notes task notes
     */
    public ToDo(String description, String notes) {
        super(description, notes);
    }

    @Override
    public String getTaskForSaving() {
        return String.format("T | %d | %s |  | %s\n", (
                this.isDone ? 1 : 0),
                this.description,
                this.notes);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s",
                super.toString(),
                this.notes.isEmpty() ? "" : String.format("(%s)", this.notes)
        );
    }
}
