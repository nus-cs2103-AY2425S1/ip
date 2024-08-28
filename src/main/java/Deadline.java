public class Deadline extends Task{
    private String by;

    public Deadline() {
        super("");
    }

    public Deadline(String taskName) {
        super(taskName.split("/by", 2)[0]);
        by = taskName.split("/by", 2)[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    public static boolean isDeadlineFormat(String format) {
        return format.startsWith("D");
    }

    @Override
    public String taskToStorageFormat() {
        return "D|" + by + "|" + super.taskToStorageFormat();
    }

    @Override
    public void initStorageFormat(String format) {
        String[] temp = format.split("\\|", 3);
        by = temp[1];
        super.initStorageFormat(temp[2]);
    }
}
