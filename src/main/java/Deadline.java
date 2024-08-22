public class Deadline extends Task {
    String dueDate;

    public Deadline(String title, String dueDate) {
        super(title);
        this.dueDate = dueDate;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + dueDate + ")";
    }
}
