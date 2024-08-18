public class Events extends Task {

    private String startDateTime;
    private String endDateTime;

    public Events(String name, String startDate, String endDate) {
        super(name);
        this.startDateTime = startDate;
        this.endDateTime = endDate;
    }

    public String getstartDate() {
        return this.startDateTime;
    }

    public String getendDate() {
        return this.endDateTime;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String print() {
        return super.print() + "(from: " + this.getstartDate() + "to: " + this.getendDate() + ")";
    }
}