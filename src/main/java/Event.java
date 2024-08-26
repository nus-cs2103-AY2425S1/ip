public class Event extends Task {
    private String start;
    private String end;

    public Event (String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String storeTask() {
        StringBuilder saveTaskInfo = new StringBuilder();
        if (this.getCompleted()) {
            saveTaskInfo.append("done, ");
        }
        else {
            saveTaskInfo.append("undone, ");
        }
        saveTaskInfo.append("event ");
        saveTaskInfo.append(this.getName());
        saveTaskInfo.append(" /from ");
        saveTaskInfo.append(this.start);
        saveTaskInfo.append(" /to ");
        saveTaskInfo.append(this.end);
        saveTaskInfo.append("\n");
        return saveTaskInfo.toString();
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + start + "to: " + end + ")";
    }
}
