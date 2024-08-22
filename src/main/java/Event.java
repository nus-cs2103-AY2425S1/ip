public class Event extends Task{
    private String from;
    private String to;

    public Event(String taskName) {
        super(taskName.split("/from", 2)[0]);
        String temp = taskName.split("/from", 2)[1];
        String[] tempArray = temp.split("/to", 2);
        from = tempArray[0];
        to = tempArray[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + from + "to:" + to + ")";
    }
}
