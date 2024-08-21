public class Deadline extends Task {
    String date = "";

    public Deadline(String desc, String date) {
        super(desc);
        this.date = date;
    }

    @Override
    public String stringValue() {
        return "[D]" + super.stringValue() + " (by: " + date + " )";
    }

}
