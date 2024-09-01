public class DeadlineTask extends Task {

    private final String deadline;

    public DeadlineTask(String description, String deadline) throws IllegalInputPotongException {
        super(description);
        if (deadline.isEmpty()) {
            throw new IllegalInputPotongException();
        }
        this.deadline = deadline;
    }

    public DeadlineTask(String description, String deadline, boolean isDone) throws IllegalInputPotongException {
        super(description, isDone);
        if (deadline.isEmpty()) {
            throw new IllegalInputPotongException();
        }
        this.deadline = deadline;
    }

    @Override
    public String getTime() {
        return this.deadline;
    }
    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
