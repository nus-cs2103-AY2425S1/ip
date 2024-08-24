public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean isDone, String task) {
        super(isDone, task);
    }

    @Override
    public String markDone() {
        super.markDone();
        return this.toString();
    }
    @Override
    public String unmarkDone() {
        super.unmarkDone();
        return this.toString();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
