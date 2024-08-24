public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskForSaving() {
        return String.format("T | %d | %s\n",
                (this.isDone ? 1 : 0),
                this.description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
