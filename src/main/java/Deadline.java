public class Deadline extends Task{
    String deadl;
    public Deadline(String descr, String dl) {
        super(descr);
        deadl = dl;
    }


    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadl + ")";
    }
}
