public class Deadline extends Task{
    private final String by;
    public Deadline(String name, String by) {
        super(name.trim());
        this.by = by.trim();
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString()
            + " (by: " + by + ")");
    }
}
