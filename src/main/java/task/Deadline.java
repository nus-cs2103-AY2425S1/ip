package task;

public class Deadline extends Task {
    private String dueDate;

    public Deadline(String title, String dueDate) {
        super(title);
        this.dueDate = dueDate;
    }

    public static Deadline of(String[] args) {
        return new Deadline(args[1], args[2]);
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toData() {
        return super.toData() + "|" + dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + dueDate + ")";
    }
}
