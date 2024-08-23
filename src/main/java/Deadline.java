public class Deadline extends Task {
    private final String typeOfTask = "[D] ";
    private String dueDate;

    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String string = " (due: " + this.dueDate + ")";
        if (taskIsDone()) {
            return typeOfTask + statusWhenDone() + taskString() + string;
        } else {
            return typeOfTask + statusWhenNotDone() + taskString() + string;
        }
    }
}
