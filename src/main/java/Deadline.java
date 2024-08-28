public class Deadline extends Task{
    protected DateTime by;
    public Deadline(String desc, String by) throws CloudException {
        super(desc);
        this.by = DateTime.of(by);
    }

    @Override
    public String formatSave() {
        return "D | "
                + super.formatSave()
                + " | " + this.by.formatSave();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
