import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface TimedTask {

    public LocalDateTime getDueDate();

    public default LocalDateTime getTime(String descriptionString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return LocalDateTime.parse(descriptionString, inputFormatter);
    };

    public default String convertTimeToString(LocalDateTime time) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
        return time.format(outputFormatter);
    };

}
