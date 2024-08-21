public class DeadlineTask extends Task{
    protected String ddl;

    public DeadlineTask(String wholeInfo) {
        super(splitInfo(wholeInfo)[0]);
        String[] info = splitInfo(wholeInfo);
        ddl = info[1].split("\\s+", 2)[1];
    }

    public static String[] splitInfo(String wholeInfo) {
        return wholeInfo.split("/", 2);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + super.description + "(by: " + ddl + ")";
    }
}
