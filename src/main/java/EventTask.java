public class EventTask extends Task{
    protected String startTime;
    protected String endTime;

    public EventTask(String wholeInfo) {
        super(splitInfo(wholeInfo)[0]);
        String[] info = splitInfo(wholeInfo);
        startTime = info[1].split("\\s+", 2)[1];
        endTime = info[2].split("\\s+", 2)[1];
    }

    public static String[] splitInfo(String wholeInfo) {
        return wholeInfo.split("/", 3);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + super.description +
                "(from: " + startTime + "to: " + endTime + ")";
    }
}
