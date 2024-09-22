package donk.task;

/**
 * Representation of a simple todo
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description, "T");
    }

    @Override
    public String getIsoDate() {
        return "0";
    }

    @Override
    public int compareTo(Task task) {
        return -1;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toFileSaveString() {
        return this.taskType + "|" + (this.isDone ? "1" : "0") + "|" + this.description;
    }

}
