package diomon;

import java.time.LocalDate;

public class Deadline extends Task{
    public static final String TYPEICON = "D";
    private LocalDate deadline;
    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }
    public Deadline(boolean complete, String description, LocalDate deadline) {
        super(complete, description);
        this.deadline = deadline;
    }
    @Override
    public String getTypeIcon() {
        return TYPEICON;
    }

    @Override
    public String toString() {
        return super.description + String.format(" (by: %s (%s))", deadline.format(Parser.DATEFORMATTER), deadline.getDayOfWeek());
    }

    @Override
    public String toStorageString(){
        return String.format("%s|%s|%s|%s", getTypeIcon(), getStatusIcon(),description, deadline.format(Parser.DATEFORMATTER));
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Deadline temp) {
            return temp.deadline.equals(deadline) && temp.completed == completed && temp.description.equals(description);
        }
        return false;
    }
}
