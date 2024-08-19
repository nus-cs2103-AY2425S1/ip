public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
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
