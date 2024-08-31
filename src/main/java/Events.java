import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Events extends Task{

    LocalDate start;
    LocalDate end;

    public Events(String taskDes, String start, String end) throws DateTimeParseException {
        super(taskDes);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toString(){
        return "[E] " + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String getAdd(){
        return " /from " + start + " /to " + end;
    }

    @Override
    public String getTypeLetter() {
        return "E";
    }

}
