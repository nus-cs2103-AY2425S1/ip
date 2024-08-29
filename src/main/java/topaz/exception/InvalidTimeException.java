package topaz.exception;

import topaz.main.Topaz;

import java.time.LocalDateTime;

public class InvalidTimeException extends InvalidTaskException {

    LocalDateTime from;
    LocalDateTime to;
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
