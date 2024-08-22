class Events extends Task {
    String start;
    String end;
    Events(String taskString, String start, String end) {
        super(taskString);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + " to: " + end + ")";
    }
}