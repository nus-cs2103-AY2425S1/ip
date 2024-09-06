package shnoop.tasks;

import shnoop.exceptions.*;

/**
 * Represents a Task that must be done by a certain time / date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a Deadline instance with the given input.
     *
     * @param description Details of Deadline.
     * @param by Time associated with the Deaadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a Deadline instance with the given input, including if it should be marked when created.
     *
     * @param description Details of Deadline.
     * @param by Time associated with the Deaadline.
     * @param done True if Deadline should be marked when created.
     */
    public Deadline(String description, String by, boolean done) {
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
    public Deadline(String str) throws IncompleteEventOrDeadlineException {
        super(str);
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
    public String toUniqueFileString() {
        String s = super.toUniqueFileString();
        s += "003"; // Unique identifier for Event Tasktype
        s += super.description;
        s += "/by/";
        s += by;
        return s;
    }
}
