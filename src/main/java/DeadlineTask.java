public class DeadlineTask extends Task {

    private String deadline;

    public DeadlineTask(String description, String deadline) throws IllegalInputPotongException {
        super(description);
        if (deadline.isEmpty()) {
            throw new IllegalInputPotongException();
        }
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
