package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public Deadline(String s, String deadline) {
        super(s);
        this.deadline = LocalDate.parse(deadline);
    }

    public String infoForFile() {
        String str = "[D] / " + super.getDetails() + " / " + deadline;
        return str;
    }

    @Override
    public String toString() {
        String str = "";
        str = str + "[D]";
        str = str + super.toString();
        str = str + String.format("(by: %s)", formatter.format(deadline));
        return str;
    }
}
