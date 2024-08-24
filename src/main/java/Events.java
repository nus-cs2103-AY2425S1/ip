public class Events extends Task {
    private String description;
    private String from;
    private String to;
    public Events (String description, String from, String to) {
        super(description);
        this.description = description;
        this.from = from;
        this.to = to;
    }

}
