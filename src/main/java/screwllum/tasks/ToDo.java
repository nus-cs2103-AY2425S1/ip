package screwllum.tasks;

public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }
    
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Convert the task to a format suitable for saving to a file.
     * 
     * @return A String in the format T_status_desc.
     */
    public String toSaveFormat() {
        return String.format("T_%s_%s", isDone ? "1" : "0", getDesc());
    }
}
