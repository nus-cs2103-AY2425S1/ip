public class Deadline extends Task{
    protected String due;
    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return String.format("[D] [%s] %s (by: %s)", mark, super.description, this.due);
    }
}
