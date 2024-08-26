public class Event extends Task {
   private String date1 = "";
   private String date2 = "";


    public Event(String desc, String date1, String date2) {
        super(desc);
        this.date1 = date1.trim();
        this.date2 = date2.trim();
    }

    public Event(String desc, String date1, String date2, boolean isDone) {
        super(desc, isDone);
        this.date1 = date1;
        this.date2 = date2;
    }

    @Override
    public String stringValue() {
        return "[E]" + super.stringValue() + "(from: " + date1 + " to: " + date2 + ")";
    }

    public String storeValue() {
        return this.stringValue().substring(1,2) + " | " + this.isTaskDone() + " | " + this.getDesc() + " | " + date1 + " | " + date2 + "\n";
    }

}
