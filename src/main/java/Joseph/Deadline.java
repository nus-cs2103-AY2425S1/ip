package Joseph;

public class Deadline extends Task {
    private final String due;

    public Deadline(String desc, String due) {
        super(desc);
        this.due = due;
    }

    public String getDue() {
        return this.due;
    }

    @Override
    public String getDesc() {
        return super.getDesc() + "due: " + this.due;
    }
}
