public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    private String getTypeLabel() {
        return "T";
    }

    public String toString() {
        return String.format("[%s]%s", this.getTypeLabel(), super.toString());
    }
}
