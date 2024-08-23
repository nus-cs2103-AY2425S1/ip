public class Event extends Task {
    private String from;
    private String to;

    public Event(String taskDesc, String from, String to) throws ConverSageException {
        super(taskDesc);

        if (taskDesc.isEmpty()) {
            throw new ConverSageException("The event description cannot be empty...");
        }

        if (from.isEmpty() || to.isEmpty()) {
            throw new ConverSageException("The from and to timings obviously can't be empty...");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
