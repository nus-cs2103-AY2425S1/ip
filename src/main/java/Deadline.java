public class Deadline extends Task{
    protected String ddl;

    public Deadline(String description, String deadline) {
        super(description);
        this.ddl = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ddl + ")";
    }

    @Override
    public String toFileFormat(){
        return String.format("D | %d | %s | %s", this.done ? 1 : 0, this.name, this.ddl);
    }
}
