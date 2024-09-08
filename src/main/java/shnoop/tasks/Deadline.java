package shnoop.tasks;

import shnoop.exceptions.*;
public class Deadline extends Task {
    protected String by;
    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline (String description, String by, boolean done) {
        super(description);
        this.by = by;
        if (done) {
            this.markTask();
        }
    }

    /**
     * Constructs new Deadline task by extracting /by from the String
     *
     * @param str Un-separated String
     */
    public Deadline (String str) throws IncompleteEventOrDeadlineException {
        if (!str.contains("/by ")) {
            throw new IncompleteEventOrDeadlineException();
        } else {
            String desc = (str.substring(9, str.toLowerCase().indexOf("/by ")));
            try {
                this.description = desc;
                this.by = parseDate(str.substring(str.toLowerCase().indexOf("/by ") + 4, str.length()));
                if (by.isEmpty() || desc.isEmpty()) {
                    throw new IncompleteEventOrDeadlineException();
                }
                isDone = false;
            } catch (IncompleteEventOrDeadlineException e) {
                System.out.println("THIS AINT IT."); //TODO PUT PROPER WORDING HERE
            }
        }
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toUString() {
        String s = super.toUString();
        s += "003"; // Unique identifier for Event Tasktype
        s += super.description;
        s += "/by/";
        s += by;
        return s;
    }
}
