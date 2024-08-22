public class Deadline extends Task {
    private String endDate;

    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public String getEndDate() {
        return this.endDate;
    }
}