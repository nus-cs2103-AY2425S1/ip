package meerkatpack;

public class Deadline extends Task {
    private String duedate;
    public Deadline(String name, String duedate) {
        super(name);
        this.duedate = duedate;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.duedate + ")";
    }

    @Override
    public String toParseableString() {
        String s = "d,";
        if (this.isCompleted()) {
            s += "m,";
        }
        else {
            s += "u,";
        }
        s += this.getName() + "," + this.duedate;
        return s;
    }
}
