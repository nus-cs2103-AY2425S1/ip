public class Deadline extends Task{

    public Deadline(String description) {
        super(description.replace("/by", "(by:") + ")");
        if (description.isEmpty() || description.equals("deadline")) {
            throw new DukeException("deadline", "OOPS!!! The description of a deadline shouldn't be empty!\n");
        }
    }

    public String getString() {
        return "[D]" + super.getString();
    }
}
