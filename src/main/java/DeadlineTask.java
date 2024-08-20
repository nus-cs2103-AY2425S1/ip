public class DeadlineTask extends Task {
    private String date;

    public DeadlineTask(String desc, String date) {
        super(desc);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusString(), getDesc(), date);
    }
}
