class Event extends Task {
    private String start;
    private String end; 
    public Event (String description, String from, String to) {
        super(description);
        this.start = from;
        this.end = to;
    }
  
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to " + end + ")";
    }
}
