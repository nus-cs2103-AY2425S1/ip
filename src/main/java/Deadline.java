public class Deadline extends Task{
    private final String endDate;

    public Deadline(String name, int count, String endDate) {
        super(name, count);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String endDateString = " (by: " + endDate + ")";
        return "[D]" + super.toString() + endDateString;
    }
}
