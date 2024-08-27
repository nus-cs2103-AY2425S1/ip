public class Deadline extends Task {
    private String type = "D";

    private static String modifyDescription(String des) throws TaskException {
        if (des.length() == 0) {
            throw new TaskException("OH NO!!! The description of Deadline cannot be empty!");
        } else if (!des.contains("/by")) {
            throw new TaskException("Deadline should be the following format: {description} /by {date}");
        }
        return des.replace("/by", "by:");
    }

    public Deadline(String description) throws TaskException {
        super(modifyDescription(description));
    }

    public Deadline(String description, String done) {
        super(description, done);
    }

    @Override
    public String getType() {
        return this.type;
    }

}
