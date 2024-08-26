public class Deadline extends Task {
    private String end;

    public Deadline(String name, String by) {
        super(name);
        this.end = by;
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
        saveTaskInfo.append("deadline ");
        saveTaskInfo.append(this.getName());
        saveTaskInfo.append(" ");
        saveTaskInfo.append(this.end);
        saveTaskInfo.append("\n");
        return saveTaskInfo.toString();
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.end + ")";
    }
}
