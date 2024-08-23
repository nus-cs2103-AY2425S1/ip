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

    // the following constructor was created using ChatGPT autocomplete
    public Deadline(String description, String deadline, boolean isDone) {
        super(description);
        this.type = "D";
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    @Override
    public String getDescription() {
        return this.description + "(" + this.deadline.replaceFirst("by", "by:") + ")";
    }

    @Override
    public String convertToFileFormat() {
        return this.type + "|" + this.isDone + "|" + this.description + "|" + this.deadline;
    }

}
