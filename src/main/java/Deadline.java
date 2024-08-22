public class Deadline extends Task {

    String time;
    public Deadline(boolean mark, String task, String time) {
        super(mark, task);
        this.time = time;
    }
    @Override
    public String toString() {
        String s;
        s = String.format("[D]%s (by: %s)", super.toString(), this.time);
        return s;
    }

}
