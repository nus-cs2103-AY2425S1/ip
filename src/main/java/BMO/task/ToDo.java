package bmo.task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSavedFormat() {
        return "T | " + (this.getIsDone() ? "1" : "0") + " | " + this.getDescription() + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo) {
            ToDo other = (ToDo) obj;
            return super.getDescription().equals(other.getDescription());
        }
        return false;
    }
}
