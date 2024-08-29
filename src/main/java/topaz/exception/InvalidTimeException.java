package topaz.exception;

import java.time.LocalDateTime;

import topaz.main.Topaz;

public class InvalidTimeException extends InvalidTaskException {

    private LocalDateTime from;
    private LocalDateTime to;
    public InvalidTimeException(Topaz.TaskType type, LocalDateTime from, LocalDateTime to) {
        super(type);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Start time: " + from + " shouldn't be later than end time: " + to;
    }
}
