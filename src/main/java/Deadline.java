public class Deadline extends Task{
    private String deadl;
    public Deadline(String descr, String dl) {
        super(descr);
        deadl = dl;
    }
    public String getDates() {
        return " | "+deadl;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadl + ")";
    }
}
