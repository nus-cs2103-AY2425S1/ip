public class Deadline extends Task{
    private final String end;
    public Deadline(String des, String end) {
        super(des);
        this.end = end;
    }

    public Deadline(String des, boolean isMark, String end) {
        super(des);
        this.end = end;
        this.isMark = isMark;
    }

    @Override
    public String getDes() {
        return "[D]" + super.getDes() + " (by: " + end + ")";
    }

    @Override
    public String toString() {
        return String.format("D %d %s | %s", isMark? 1 : 0, des, end);
    }
}
