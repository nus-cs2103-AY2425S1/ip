public class Deadline extends Task{
    private String deadline;
    public Deadline(String task, String by) {
        super(task);
        this.deadline = by;
    }

    @Override
    public String getTypeIcon() {
        return "D";
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
