public class Event extends Task{
    private String from;
    private String to;

    public Event() {
        super("");
    }

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

    public static boolean isEventFormat(String format) {
        return format.startsWith("E");
    }

    @Override
    public String taskToStorageFormat() {
        return "E|" + from + "|" + to + "|" + super.taskToStorageFormat();
    }

    @Override
    public void initStorageFormat(String format) {
        String[] temp = format.split("\\|", 4);
        from = temp[1];
        to = temp[2];
        super.initStorageFormat(temp[3]);
    }
}
