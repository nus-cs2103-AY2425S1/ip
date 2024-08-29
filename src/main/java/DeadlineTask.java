public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String getStorageRepresentation() {
        if (this.isdone) {
            return "D|1|" + this.description + "|" + this.deadline;
        } else {
            return "D|0|" + this.description + "|" + this.deadline;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[D]");
        sb.append(super.toString());
        sb.append(" (by: " + this.deadline + ")");
        return sb.toString();
    }
}