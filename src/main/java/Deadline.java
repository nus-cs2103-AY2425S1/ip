public class Deadline extends Task{

    public Deadline(String description) {
        super(description.replace("/by", "(by:") + ")");
    }

    public String getString() {
        return "[D]" + super.getString();
    }
}
