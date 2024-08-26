import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Events extends Tasks {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' hh:mm a");

    public Events(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    protected String typeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return typeIcon() + super.toString() + "(from: " + FORMATTER.format(startDate) + " to: " + FORMATTER.format(endDate)+ ")";
    }

    public static Events CreateEvent(String text) throws InvalidDateException ,NoDescriptionException {
        int descriptionEnd = text.indexOf('/');
        String description = text.substring(6, descriptionEnd).trim();

        if (descriptionEnd == -1 || description.isEmpty()) {
            throw new NoDescriptionException("No description");
        }

        String dateSubstring = text.substring(descriptionEnd + 1).trim();
        if (!dateSubstring.startsWith("from")) {
            throw new InvalidDateException(text);
        }

        int startDateEnd = dateSubstring.indexOf('/');
        String startDate = dateSubstring.substring(4, startDateEnd).trim();
        LocalDateTime startDateTime = TimeConverter.timeConverter(startDate);

        String endDateCommand = dateSubstring.substring(startDateEnd + 1);
        if (!endDateCommand.startsWith("to")) {
            throw new InvalidDateException(text);
        }

        String endDate = endDateCommand.substring(2).trim();
        LocalDateTime endDateTime = TimeConverter.timeConverter(endDate);

        return new Events(description, startDateTime, endDateTime);
    }

    @Override
    protected String saveFormat() {
        return "E | " + (super.isDone ? "1 | " : "0 | ") + description + " | " + startDate + " | " + endDate;
    }
}


