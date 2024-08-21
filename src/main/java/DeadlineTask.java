public class DeadlineTask extends Task {
    private String dueDate;

    DeadlineTask(String tastName, String dueDate) {
        super(tastName);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(Due by: " + this.dueDate + ")";
    }
}
