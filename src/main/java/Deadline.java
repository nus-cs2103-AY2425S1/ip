class Deadline extends Task {
    private final String dueDate;

    Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.name
                + " (by: " + this.dueDate + ")";
    }
}
