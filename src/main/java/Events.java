class Events extends Task {
    String start;
    String end;
    Events(String taskString, String start, String end) {
        super(taskString);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileString() {
        return "E | " + (done ? 1 : 0) +" | " + taskString + " | " + start + "-" + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + " to: " + end + ")";
    }
}