import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDateTime by;

    public Deadline(String desc, LocalDateTime by){
        super(desc);
        this.by = by;
    }

    public String stringifyTask() {
        return String.format("D | %d | %s | %s", super.getStatus() ? 1 : 0,
                super.getDesc(),
                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + ")";
    }
}
