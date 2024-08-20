public class DeadlineTask extends Task{

    String deadline;
    public DeadlineTask(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String s = "";
        s += "[D]";
        s += super.toString();
        s += super.taskDesc;
        s += String.format(" (by: %s)", this.deadline);
        return s;
    }
}
