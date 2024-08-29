public class Deadline extends Task {

    private final String deadline;

    public Deadline(String taskInfo, String deadline) {
        super(taskInfo);
        this.deadline = deadline;
    }

    @Override
    public String stringIsDone() {
        if (checkIsDone()) {
            return String.format("[D][X] %s (by: %s)", super.getTask(), this.deadline);
        } else {
            return String.format("[D][ ] %s (by: %s)", super.getTask(), this.deadline);
        }
    }

}
