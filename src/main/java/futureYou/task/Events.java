package futureYou.task;

public class Events extends Task {

    private String startDateTime;
    private String endDateTime;

    public Events(String name, String startDate, String endDate) {
        super(name);
        this.startDateTime = startDate;
        this.endDateTime = endDate;
    }

    public Events(String name, Boolean taskStatus, String startDate, String endDate) {
        super(name, taskStatus);
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
    public String toString() {
        return super.toString() + "|" + this.getstartDate() + "|" + this.getendDate();
    }

    @Override
    public String print() {
        return super.print() + "(from: " + this.getstartDate() + "to: " + this.getendDate() + ")";
    }
}