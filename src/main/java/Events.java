public class Events extends Task {
    private String startDate;
    private String endDate;

    public Events(String name,  String start, String end) {
        super(name);
        this.startDate = start;
        this.endDate = end;
    }

    @Override
    public String toString() {
        String myEvent = "[ E ] ";

        if (this.isDone()) {
            myEvent += "[ X ]";
        } else {
            myEvent += "[   ]";
        }

        return myEvent + super.toString() + " (from: " + this.startDate + " to: " + this.endDate + " )";
    }
}
