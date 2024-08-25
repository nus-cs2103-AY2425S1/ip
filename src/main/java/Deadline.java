class Deadline extends Task {
    private final String dueDate;

    Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    Deadline(String name, String dueDate, boolean isDone) {
        super(name, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] " + this.name
                + " (by: " + this.dueDate + ")";
    }

    @Override
    String toSaveState() {
        return "D" + "/" + (this.isDone ? "1" : "0") + "/"
                + this.name + "/" + this.dueDate + "\n";
    }
}
