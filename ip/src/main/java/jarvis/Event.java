package jarvis;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    String start;
    String end;
    LocalDate dateStart;
    LocalDate dateEnd;
    public Event(String name, String start, String end){
        super(name);
        try {
            this.dateStart = LocalDate.parse(start);
        } catch(DateTimeParseException e) {
            this.start = start;
        }
        try {
            this.dateEnd = LocalDate.parse(end);
        } catch(DateTimeParseException e) {
            this.end = end;
        }
    }

    public String getStart() {
        return start == null
                ? dateStart.toString()
                : start;
    }
    public String getEnd() {
        return end == null
                ? dateEnd.toString()
                : end;
    }
    public String toString(){
        String checkbox = this.isDone() ? "[X]" : "[ ]";
        String result = String.format(" %s %s  from: %s, to %s\n",
                checkbox, this.getName(), this.getStart(), this.getEnd());
        return "[E]" + result;
    }
}
