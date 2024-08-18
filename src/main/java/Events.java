public class Events extends Task {

    private String startDate;
    private String endDate;

    public Events(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getstartDate() {
        return this.startDate;
    }

    public String getendDate() {
        return this.endDate;
    }

    @Override
    public String getType() {
        return "E";
    }
}