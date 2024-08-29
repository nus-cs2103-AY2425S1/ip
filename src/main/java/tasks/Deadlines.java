package tasks;

public class Deadlines extends Task {
    private String deadline;
    public Deadlines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getAdditionalInfo() {
        return deadline;
    }

    @Override
    public String toFileString() {
        return getType() + "|" + getName() + "|" + isComplete() + "|" + getAdditionalInfo();
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(" + deadline + ")";
    }
}
