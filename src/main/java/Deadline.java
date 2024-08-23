public class Deadline extends Task {
    private String completeBy;

    public Deadline(String description, String completeBy) {
        super(description);
        this.completeBy = completeBy;
        
    }

    public String toString() {
        return "[D]" + super.toString() + "(by:" + completeBy + ")";
    }
}