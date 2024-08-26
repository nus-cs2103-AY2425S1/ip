import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    String time_start;
    String time_end;

    public Event(boolean mark, String task,
                 String time_start, String time_end) {
        super(mark, task);
        this.time_start = time_start;
        this.time_end = time_end;

    }

    @Override
    public void hasDate() throws BoombotrozException {
        if (time_start.matches("\\d{4}-\\d{2}-\\d{2}")
                && time_end.matches("\\d{4}-\\d{2}-\\d{2}")) {
            LocalDate d1 = LocalDate.parse(time_start);
            LocalDate d2 = LocalDate.parse(time_end);
            LocalDate d3 = LocalDate.now();
            if (d1.isAfter(d2) || d3.isAfter(d2)) {
                throw new BoombotrozException("Your date is wrong !!");
            }
            time_start = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            time_end = d2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        }
    }


    @Override
    public String toString() {
        String s;
        s = String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.time_start, this.time_end);
        return s;
    }

}
