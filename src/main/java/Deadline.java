package main.java;

public class Deadline extends Task {
    private String deadline;
    public Deadline(String description, String deadline) {
        super(description, "D");
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String superString = super.toString();
        String dueDate = String.format(" (by: %s)", this.deadline);
        return superString + dueDate;
    }
}
