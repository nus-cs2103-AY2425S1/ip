class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return String.format("D | %d | %s | %s",
                isDone ? 1 : 0,
                description,
                by);
    }
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}