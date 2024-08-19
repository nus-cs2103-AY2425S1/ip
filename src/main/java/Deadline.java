public class Deadline extends Task{
    private final String end;
    public Deadline(String des, String end) {
        super(des);
        this.end = end;
    }

    @Override
    public String getDes() {
        return "[D]" + super.getDes() + " (by: " + end + ")";
    }
}
