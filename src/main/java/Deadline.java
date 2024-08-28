import java.io.IOException;

public class Deadline extends Task{

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    protected void save() throws IOException {
        Storage.getWriter().write("D | " + (super.getIsDone() ? "1" : "0") + " | " + super.getDescription() + " | " + by + "\n");
    }
}