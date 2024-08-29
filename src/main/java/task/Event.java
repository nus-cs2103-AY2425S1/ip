package task;
import exception.MaxineException;
import java.util.ArrayList;

public class Event extends Task {

    protected String start;
    protected String end;
    public Event() {
        super();
    }

    public Event(String description, String start, String end) {
        super(description);
        try {
            this.start = dateTimeParser(start.trim());
            this.end = dateTimeParser(end.trim());
        } catch (Exception e) {
            this.start = start;
            this.end = end;
        }
    }
    
    public void addToEvent(String[] arr, ArrayList<Task> list) throws MaxineException {
        StringBuilder desc = new StringBuilder();
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        desc.append(arr[1]);
        boolean hasFrom = false;
        boolean hasTo = false;
        for (int i = 2; i < (arr.length - 1); i++) {
            if (arr[i].equals("/from")) {
                hasFrom = true;
            }
            if (arr[i].equals("/to")) {
                hasTo = true;
            }
        }

        if (!hasFrom || !hasTo || arr[1].equals("/from")) {
            throw new MaxineException("Please follow this format: " +
                    "event [enter event] /from [start date] /to [end date]");
        }
        boolean isAfterFrom = false;
        boolean isAfterTo = false;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i].equals("/from")) {
                isAfterFrom = true;
            }
            else if (arr[i].equals("/to")) {
                isAfterFrom = false;
                isAfterTo = true;
            } else if (isAfterFrom) {
                String word = " " + arr[i];
                start.append(word);
            } else if (isAfterTo) {
                String word = " " + arr[i];
                end.append(word);
            } else {
                String word = " " + arr[i];
                desc.append(word);
            }
        }
        Event task = new Event(desc.toString(), start.toString(), end.toString());
        list.add(task);
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + description
                + " (From: " + start + " | To: " + end + ")";
    }

    @Override
    public String writeToFile() {
        return "E" + super.writeToFile() + " / " + start + " / " + end;
    }

}

