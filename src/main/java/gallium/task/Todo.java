package gallium.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    protected String desc;

    /**
     * Constructs a Todo task with a description.
     * 
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
        if (description.startsWith("todo ")) {
            this.desc = description.substring(5).trim();
        } else if (description.startsWith("[T][ ] ")) {
            this.desc = description.substring(7).trim();
            this.isDone = false;
        } else if (description.startsWith("[T][X] ")) {
            this.desc = description.substring(7).trim();
            this.isDone = true;
        }
    }

    /**
     * Returns a string representation of the Todo task.
     * 
     * @return A string representation of the Todo task in the format "[T][X/
     *         ][description]".
     */
    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + this.desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
