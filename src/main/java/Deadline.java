public class Deadline extends Task {
    private String date = "";

    public Deadline(String desc, String date) {
        super(desc);
        this.date = date;
    }

    public Deadline(String desc, String date, boolean isDone) {
        super(desc, isDone);
        this.date = date;
    }

    public String storeValue() {
        return this.stringValue().substring(1,2) + " | " + this.isTaskDone() + " | " + this.getDesc() + " | " + date + "\n";
    }

    @Override
    public String stringValue() {
        return "[D]" + super.stringValue() + " (by: " + date + " )";
    }

}
