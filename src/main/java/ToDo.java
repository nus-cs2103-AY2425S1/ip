public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String parseTaskInfo() {
        return "T, "
                + (this.isCompleted ? "1, " : "0, ")
                + this.name
                + "\n";
    }
}
