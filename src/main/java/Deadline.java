package main.java;

public class Deadline extends Task{
    private String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "[D]" + s + String.format("(by: %s)", this.dueDate);
    }

    @Override
    public String toFileString() {
        int markedInt = this.isMarked() ? 1 : 0;
        return String.format("D | %d | %s", markedInt, this.dueDate);
    }
}
