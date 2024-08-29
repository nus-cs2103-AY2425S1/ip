package duke;
import java.time.LocalDate;

public class Deadline extends Task {
    LocalDate deadl;
    public Deadline(String descr, String dl) {
        super(descr);
        deadl = LocalDate.parse(dl);
    }

    public String getDates() {
        return " | " + deadl;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + super.dateConverter(deadl) + ")";
    }
}


