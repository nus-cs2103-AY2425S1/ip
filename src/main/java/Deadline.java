public class Deadline extends Task {
    String deadline;
    public Deadline(String desc, String deadline) {
        super(desc);
        int indexOfSpace = deadline.indexOf(" ");
        String temp = deadline.substring(indexOfSpace + 1); // remove "by"
        this.deadline = temp;
    }

    @Override
    public String getDesc() {
        return String.format("  [D][%s] %s (by: %s)", super.getStatusIcon(), super.getDesc(), this.deadline);
    }
}
