package futureYou.task;

public class Deadline extends Task {
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    public Deadline(String name, Boolean taskStatus, String date) {
        super(name, taskStatus);
        this.date = date;
    }

    public String getDeadline() {
        return this.date;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + "|" + this.getDeadline();
    }

    @Override
    public String print() {
        return super.print() + "(by: " + this.getDeadline() + ")";
    }
}