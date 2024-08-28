public class Deadline extends Task {

    private String by;

    public String getBy() {
        return this.by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Deadline(boolean done, String task, String by) {
        super(done, task);
        this.by = by;
    }

    @Override
    public String toString() {
        String task = super.getTask() + " " + String.format("(by: %s)", by);
        return super.getDone() ? "[D][X] " + task : "[D][ ] " + task;
    }

}
