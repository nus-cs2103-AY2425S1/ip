public class EventTask extends Task {
    String start;
    String end;

    public EventTask(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTaskType() {
        return "E";
    }
    @Override
    public String getDescription() {
        return this.getTaskType() + " | " +
                super.getDescription().replace("\n", "") +
                "| " + String.format("from:%s to:%s", this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s",
                this.getTaskType(), getStatusIcon(), super.description, this.start, this.end);
    }
}
