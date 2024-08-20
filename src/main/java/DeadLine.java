public class DeadLine extends Task{

    private final String to;
    public DeadLine(String _description, String _to) {
        super(_description);
        this.to = _to;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + this.to + ")";
    }
}
