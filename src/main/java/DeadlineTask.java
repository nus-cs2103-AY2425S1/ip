public class DeadlineTask extends Task {
    String deadline;

    public DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getDescription() {
        return this.getTaskType() + " | " +
                super.getDescription().replace("\n", "") + "|" +
                String.format("%s", this.deadline) ;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s",
                this.getTaskType(), getStatusIcon(), super.description, this.deadline);
    }

}
