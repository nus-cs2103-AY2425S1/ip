public class DeadlineTask extends Task {
    public DeadlineTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
