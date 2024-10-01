package jarvis.logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    LocalDate dateDeadline;
    String deadline;
    public Deadline(String name, String deadline) throws NullPointerException{
        super(name);
        if(name.trim().equals("")){
            throw new NullPointerException();
        }
        if(deadline.equals("")){
            throw new NullPointerException();
        }
        try {
            this.dateDeadline = LocalDate.parse(deadline.trim());
        } catch(DateTimeParseException e){
            this.deadline = deadline;
        }
    }

    public String getDeadline() {
        return deadline == null
                ? "by: " + dateDeadline.toString() + " (" + dateDeadline.getDayOfWeek() + ")"
                : "by: " + deadline;
    }
    @Override
    public String toString(){
        String checkbox = this.isDone() ? "[X]" : "[ ]";
        String result = String.format(" %s %s %s\n", checkbox, this.getName(), this.getDeadline());
        return "[D]" + result;
    }
}
