public class Deadline extends Task{

    String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        if (description.isEmpty() || description.equals("deadline")) {
            throw new DukeException("deadline", "OOPS!!! The description of a deadline shouldn't be empty!\n");
        }
        this.deadline = deadline;
    }

    public String getString() {
        return "[D]" + super.getString() + " (by: " + deadline + ")";
    }
}
