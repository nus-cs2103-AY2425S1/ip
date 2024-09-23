package jarvis.logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    String start;
    String end;
    LocalDate dateStart;
    LocalDate dateEnd;
    public Event(String name, String start, String end) throws NullPointerException{
        super(name);
        start = start.trim();
        end = end.trim();
        if(name.trim().equals("")){
            throw new NullPointerException();
        }
        if(start.equals("") || end.equals("")) {
            throw new NullPointerException();
        }
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
                ? "from: " + dateStart +  " (" + dateStart.getDayOfWeek() +")"
                : "from: " + start;
    }
    public String getEnd() {
        return end == null
                ? "to: " + dateEnd +  " (" + dateEnd.getDayOfWeek() +")"
                : "to: " + end;
    }
    public String toString(){
        String checkbox = this.isDone() ? "[X]" : "[ ]";
        String result = String.format(" %s %s\n %s\n %s\n",
                checkbox, this.getName(), this.getStart(), this.getEnd());
        return "[E]" + result;
    }
}
