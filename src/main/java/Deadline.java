public class Deadline extends Task {
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    public String getDeadline() {
        return this.date;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String print() {
        return super.print() + "(by: " + this.getDeadline() + ")";
    }
}