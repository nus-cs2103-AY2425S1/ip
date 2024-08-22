public class Deadline extends Task {
    private String dateTime;

    public Deadline(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String stringify() {
        String str = "[D]";
        if (super.isDone()) {
            str += "[X]";
        } else {
            str += "[ ]";
        }
        str += (" " + super.getName() + "(by: " + this.dateTime + ")\n");
        return str;
    }
}
