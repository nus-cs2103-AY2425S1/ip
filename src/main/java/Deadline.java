public class Deadline extends Task{
    protected String by;
    
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    
    public String storageFormat() {
        return "D " + super.storageFormat() + " | " + by + "\n";
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")" + "\n";
    }
}
