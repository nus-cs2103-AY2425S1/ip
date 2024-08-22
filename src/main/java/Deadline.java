package main.java;

public class Deadline extends Task{
    protected String by;
    public Deadline(String by, String desc) {
        super('D', desc);
        this.by = by;
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " (by: " + by + ")";
    }
}
