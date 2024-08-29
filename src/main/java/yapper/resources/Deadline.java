package yapper.resources;

import yapper.exceptions.YapperException;

public class Deadline extends Task {
    private String by;

    public Deadline(String desc, String by) {
        super(desc);
        if (by.isEmpty()) {
            throw new YapperException("Description cannot be empty");
        }
        this.by = by;
    }

    @Override
    public String getDesc() {
        return "| D | " + super.getDesc() + " | " + formattedDate(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDate(this.by) + ")";
    }
}
