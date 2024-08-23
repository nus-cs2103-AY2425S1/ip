public class Deadline extends Task {

    protected String by;
    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Deadline(String description, String by) {
        super(description);
        this.description = extractDescription(this.description, "deadline");
        this.by = by;
        this.marker = "/by";
    }

    // Returns the letter representing deadline.
    @Override
    public String taskLetter() {
        return "D";
    }

}
