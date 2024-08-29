package jarvis;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    LocalDate dateDeadline;
    String deadline;
    public Deadline(String name, String deadline){
        super(name);
        try {
            this.dateDeadline = LocalDate.parse(deadline);
        } catch(DateTimeParseException e){
            this.deadline = deadline;
        }
    }

    public String getDeadline() {
        return deadline == null
                ? dateDeadline.toString()
                : deadline;
    }
    @Override
    public String toString(){
        String checkbox = this.isDone() ? "[X]" : "[ ]";
        String result = String.format(" %s %s %s\n", checkbox, this.getName(), this.getDeadline());
        return "[D]" + result;
    }
}
