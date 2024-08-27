import java.util.Objects;

public class DeadlineTask extends Task{

    String deadline;
    public DeadlineTask(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public DeadlineTask(String desc, String deadline, String status) {
        this(desc, deadline);
        if (Objects.equals(status, "1")) {
            super.markAsComplete();
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += "[D]";
        s += "[" + super.getStatusString() + "] ";
        s += super.getTaskDesc();
        s += String.format(" (by: %s)", this.deadline);
        return s;
    }

    @Override
    public String getStorageString() {
        String s = "";
        s += "D";
        s += Storage.SPECIAL_CHAR;
        s += super.getStatusInt();
        s += Storage.SPECIAL_CHAR;
        s += super.getTaskDesc();
        s += Storage.SPECIAL_CHAR;
        s += deadline;
        s += Storage.SPECIAL_CHAR;
        return s;
    }
}
