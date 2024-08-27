package main.java;

public class Deadline extends Task {
    private String deadline;
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, "D", isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String superString = super.toString();
        String dueDate = String.format(" (by: %s)", this.deadline);
        return superString + dueDate;
    }
}
