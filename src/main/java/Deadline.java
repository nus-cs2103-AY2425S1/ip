public class Deadline extends Task {

    private String deadline;

    public Deadline(String s, String deadline) {
        super(s);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String str = "";
        str = str + "[D]";
        str = str + super.toString();
        str = str + String.format("(by: %s)", deadline);
        return str;
    }
}
