public class eventTask extends Task{
    private String starts,ends;

    eventTask(String status, String starts, String ends){
        super(status, TaskType.EVENT);
        this.starts = starts;
        this.ends = ends;
    }

    String getStarts() {
        return starts;
    }

    String getEnds() {
        return ends;
    }

    @Override
    public String toString() {
        String icon = "[E]";
        return icon + super.toString() + " (from: " + starts + "  to: " + ends + ")";
    }
}
