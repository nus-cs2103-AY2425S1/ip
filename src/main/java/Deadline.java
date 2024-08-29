public class Deadline extends Task {

    protected String by;
    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.marker = "/by";
    }

    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.marker = "/by";
    }

    // Returns the letter representing deadline.
    @Override
    public String taskLetter() {
        return "D";
    }

    /**
     * Returns a string representation of the file format in which we store the Deadline.
     */
    @Override
    public String fileFormat () {
        String part1 = super.fileFormat();
        return part1 + " | " + by;
    }
}
