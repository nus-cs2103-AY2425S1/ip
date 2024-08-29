public class Deadline extends Task {

    private String deadline;

    public Deadline(String s, String deadline) {
        super(s);
        this.deadline = deadline;
    }

    public String infoForFile() {
        String str = "[D] / " + super.getDetails() + " / " + deadline;
        return str;
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
