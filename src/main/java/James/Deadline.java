package james;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
class Deadline extends Task{
    private LocalDateTime deadline;
    private final String PATTERN = "MMM d yyyy HHmm";
    public Deadline(String desc, Boolean mark, LocalDateTime deadline) throws MissingDescriptionException{
        super(desc, mark);
        this.deadline = deadline;
    }
    @Override
    public String printTask() {
        return String.format("[D]%s (by: %s)", super.printTask(),
                deadline.format(DateTimeFormatter.ofPattern(PATTERN)));
    }

    @Override
    public String convertToFileFormat() {
        return String.format("D | %s | %s", super.convertToFileFormat(),
                deadline);
    }
}
