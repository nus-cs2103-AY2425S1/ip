public class Deadline extends Task {

    private final String deadline;

    public Deadline(String description) {
        super();
        String[] descriptionString = description.split("/");
        this.description = descriptionString[0];
        this.deadline = descriptionString[1];
        this.type = "D";
    }

    @Override
    public String getDescription() {
        return this.description + "(" + this.deadline.replaceFirst("by", "by:") + ")";
    }

}
