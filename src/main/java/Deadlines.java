public class Deadlines extends Tasks {

    private String date;

    public Deadlines(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    protected String typeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return typeIcon() + super.toString() + "(" + date + ")";
    }

}
