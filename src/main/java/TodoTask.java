public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        return "T|" + this.getStatusIcon() + "|" + this.getDescription();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
