package duck.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Datable {
    default String getFileDateString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    };
    default String getDisplayDateString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    };
}
