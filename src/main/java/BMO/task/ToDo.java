package bmo.task;
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
}
