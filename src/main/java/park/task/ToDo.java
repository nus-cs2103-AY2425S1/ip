package park.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String encode() {
        return "T/" + this.getStatusIcon() + "/" + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
