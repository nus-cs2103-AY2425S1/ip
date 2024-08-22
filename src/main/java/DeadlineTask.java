public class DeadlineTask extends Task {
    String deadline;

    public DeadlineTask(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[D]");
        sb.append(super.toString());
        sb.append(" (by: " + this.deadline + ")");
        return sb.toString();
    }
}