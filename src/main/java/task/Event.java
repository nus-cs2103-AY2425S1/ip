package task;

import exceptions.AlreadyCompletedException;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String title, String start, String end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    public static Event of(String[] args) throws AlreadyCompletedException {
        Event event = new Event(args[2], args[3], args[4]);
        if (Boolean.parseBoolean(args[1])) {
            event.complete();
        }
        return event;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toData() {
        return super.toData() + "|" + start + "|" + end;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), start, end);
    }
}
