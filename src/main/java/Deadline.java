public class Deadline extends Task {
    private final String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    char getTaskType() {
        return 'D';
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.by);
    }
}
