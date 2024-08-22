package main.java;

public class DeadLine extends Task {

    protected String endDate;

    public DeadLine (String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String res = "[D]";
        res += super.toString();
        res += "(by: " + this.endDate + ")";
        return res;
    }
}
