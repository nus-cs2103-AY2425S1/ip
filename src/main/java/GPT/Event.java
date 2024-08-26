package GPT;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        if (from != null && to != null) {
            return super.toString() + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
        } else {
            return super.toString() + " (from: [Invalid Date] to: [Invalid Date])";
        }
    }

    @Override
    public String toFileFormat() {
        if (from != null && to != null) {
            return String.format("E | %d | %s | %s | %s",
                    isDone ? 1 : 0,
                    description,
                    from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                    to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        } else {
            return String.format("E | %d | %s | [Invalid Date] | [Invalid Date]",
                    isDone ? 1 : 0,
                    description);
        }
    }
    }