package evelyn.task;

import java.time.LocalDate;

public class Deadline extends Task{
    private LocalDate date = null;
    private String time = null;
    public Deadline(String desccription, String deadline, boolean isMarked) {
        super(desccription, isMarked);
        if (deadline.contains(" ")) {
            String[] parts = deadline.split(" ");
            this.date = LocalDate.parse(parts[0]);
            this.time = parts[1];
        } else {
            this.date = LocalDate.parse(deadline);
        }
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toString() +
                (time == null? "" : " "+time) + ")";
    }
}
