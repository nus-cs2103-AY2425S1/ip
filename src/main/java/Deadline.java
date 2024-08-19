public class Deadline extends Task {
    private final String by;

    public Deadline(String str) {
        super(str.split("/")[0].split(" ", 2)[1]);
        String[] temp = str.split("/");
        by = temp[1].split(" ", 2)[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + by + ")";
    }
}
