public class Deadline extends Task {

    String end;

    public Deadline(String str, String end) {
        super(str);
        this.end = end.substring(0, 2) + ":" + end.substring(2, end.length());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + end + ")";
    }

}
