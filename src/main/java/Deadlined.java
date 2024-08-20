public class Deadlined extends Task {
    private String deadline;

    public Deadlined(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String myDeadline = "[ D ] ";

        if (this.isDone()) {
            myDeadline += "[ X ]";
        } else {
            myDeadline += "[   ]";
        }

        return myDeadline + super.toString() + " (by: " + this.deadline + ")";
    }

}
