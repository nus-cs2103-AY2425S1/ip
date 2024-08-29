package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    private LocalDate start;
    private LocalDate end;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public Events(String s, String start, String end) {
        super(s);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    public String infoForFile() {
        String str = "[E] / " + super.getDetails();
        str += " / " + start + " / " + end;
        return str;
    }

    @Override
    public String toString() {
        String str = "";
        str = str + "[E]";
        str = str + super.toString();
        str = str + String.format("(from: %s to: %s)",
                formatter.format(start), formatter.format(end));
        return str;
    }
}
