public class Deadline extends Task {
    protected String byTime;

    public Deadline (String TaskName, String byTime) {
        super(TaskName);
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), this.byTime);
    }
}
