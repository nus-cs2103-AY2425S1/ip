class Deadline extends Task {
    protected String byWhen;

    public Deadline(String name, String byWhen) {
        super(name);
        this.byWhen = byWhen;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + name + " | " + byWhen;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byWhen + ")";
    }
}