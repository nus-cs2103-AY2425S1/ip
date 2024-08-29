package task;

import exceptions.AlreadyCompletedException;

public class Deadline extends Task {
    private String dueDate;

    public Deadline(String title, String dueDate) {
        super(title);
        this.dueDate = dueDate;
    }

    public static Deadline of(String[] args) throws AlreadyCompletedException {
        Deadline deadline = new Deadline(args[2], args[3]);
        if (Boolean.parseBoolean(args[1])) {
            deadline.complete();
        }
        return deadline;
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
