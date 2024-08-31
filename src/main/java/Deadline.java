public class Deadline extends Task {
    protected String byTime;

    public Deadline (String taskName, String byTime) {
        super(taskName);
        this.byTime = byTime;
    }

    public Deadline (String taskName, String byTime, boolean isDone) {
        super(taskName, isDone);
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), this.byTime);
    }

    @Override
    public String commaString() {
        return String.format("D,%s,%s",
                super.commaString(), this.byTime);
    }
}
