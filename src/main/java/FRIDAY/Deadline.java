package FRIDAY;

import java.time.LocalDate;

public class Deadline extends Task{
    private LocalDate taskDeadline;

    public Deadline(String description, String deadline, int type) {
        super(description, type);
        //accept deadline in YYYY-MM-DD format
        this.taskDeadline = LocalDate.parse(deadline.trim());
    }
    //store deadline in YYYY-MM-DD format
    public String storageDisplay() {
        return "D" + super.storageDisplay() + " | " + taskDeadline;
    }
    @Override
    public String toString() {
        //print it out in MM-DD-YYYY format
        return "[D]" + super.toString() + "(" + taskDeadline.getMonth() + " " + taskDeadline.getDayOfMonth() + " " + taskDeadline.getYear() + ")";
    }

    public LocalDate getTaskDeadline() {
        return this.taskDeadline;
    }
}
