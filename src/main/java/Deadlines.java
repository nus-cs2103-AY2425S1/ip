import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{

    private final LocalDate deadlineDate;
    private LocalTime deadlineTime;

    public Deadlines(String taskName, LocalDate deadlineDate) {
        super(taskName);
        this.deadlineDate = deadlineDate;
    }
    public Deadlines(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String export() {
        return String.format("deadline %s /by %s%s", super.export(),
                this.deadlineDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                this.deadlineTime != null ? " " + this.deadlineTime.format(DateTimeFormatter.ofPattern("HHmm")) : "");
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s%s)","D", super.toString(),
                this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.deadlineTime != null ? " " + this.deadlineTime : "");
    }
}


// 1.[T][ ] asdklj
// 2.[D][ ] asd (by: Dec 19 2001 18:00)
// 3.[D][ ] asd  (by: Dec 19 2002)
// 4.[E][ ] asd (from: Dec 19 2003 19:00 to: Dec 22 2003 22:00)
// 5.[E][ ] asd (from: Dec 19 2003 19:00 to: Dec 22 2003)
// 6.[E][ ] asd (from: Dec 19 2003 19:00 to: Dec 22 2004)
// 7.[E][ ] asd (from: Dec 19 2003 to: Dec 22 2003 22:00)
// 8.[E][ ] asd (from: Dec 19 2003 to: Dec 22 2003)