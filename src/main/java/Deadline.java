public class Deadline extends Task {

    private final String deadline;

    public Deadline(String description) {
        super(description);
        String[] descriptionString = description.split("/");
        this.description = descriptionString[0];
        this.deadline = descriptionString[1];
        this.type = "D";
        if (descriptionString.length > 2) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public String getDescription() {
        return this.description + "(" + this.deadline.replaceFirst("by", "by:") + ")";
    }

}
