public class Deadline extends Task{
    public static final String TYPEICON = "D";
    private String deadline;
    public Deadline(String task, String by) {
        super(task);
        this.deadline = by;
    }
    public Deadline(boolean complete, String description, String deadline) {
        super(complete, description);
        this.deadline = deadline;
    }
    @Override
    public String getTypeIcon() {
        return TYPEICON;
    }

    @Override
    public String toString() {
        return super.description + String.format(" (by: %s)", this.deadline);
    }

    @Override
    public String toStorageString(){
        return String.format("%s|%s|%s|%s", getTypeIcon(), getStatusIcon(),this.description, this.deadline);
    }
}
