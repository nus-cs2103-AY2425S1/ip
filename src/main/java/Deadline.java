public class Deadline extends Task {

    private String deadline;
    public Deadline(String input, String deadline) {
        super(input);
        this.deadline = deadline;
    }

    @Override
    public String displayTask() {
        String cross ="";
        if (super.getDone()) {
            cross = "[X]";
        } else {
            cross = "[ ]";
        }
        return "[D]" + cross + " " + super.getInput() + "(by:" + this.deadline + ")\n";
    }

}
