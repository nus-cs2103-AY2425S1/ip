package GPT;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        if (by != null) {
            return String.format("D | %d | %s | %s",
                    isDone ? 1 : 0,
                    description,
                    by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        } else {
            return String.format("D | %d | %s | [Invalid Date]",
                    isDone ? 1 : 0,
                    description);
        }
    }

    @Override
    public String toString() {
        if (by != null) {
            return super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
        } else {
            return super.toString() + " (by: [Invalid Date])";
        }
    }
}