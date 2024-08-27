public class Events extends Task {
    private String startDate;
    private String endDate;

    public Events(String task, String startDate, String endDate) {
        super(task);
        if (startDate.equals(" ") || endDate.equals(" ")){
            throw new IllegalArgumentException("\t Oh no!!(;-;) Event period cannot be empty" +
                    "\n\t Enter the event in the format: event <Task> " +
                    "/from <Start Date/Time> /to <End Date/Time>");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(from:" + this.startDate +
                "to:" + this.endDate + ")";
    }
}
