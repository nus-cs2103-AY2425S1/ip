package beeboo.task;

import beeboo.components.TimeConverter;
import beeboo.exception.InvalidDateException;
import beeboo.exception.NoDescriptionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Tasks {

    private LocalDateTime date;
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' hh:mm a");

    public Deadlines(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    protected String typeIcon() {
        return "[D]";
    }

    public static Deadlines createDeadline(String text) throws NoDescriptionException, InvalidDateException {
        int descriptionEnd = text.indexOf('/');
        String description = text.substring(0, descriptionEnd).trim();

        if (descriptionEnd == -1 || description.isEmpty()) {
            throw new NoDescriptionException("No description");
        }

        String dateCommand = text.substring(descriptionEnd + 1).trim();
        if (!dateCommand.startsWith("by")) {
            throw new InvalidDateException(text);
        }
        String date = dateCommand.substring(2).trim();
        LocalDateTime dateTime = TimeConverter.timeConverter(date);
        return new Deadlines(description, dateTime);
    }

    @Override
    public String toString() {
        return typeIcon() + super.toString() + "(by:" + FORMATTER.format(date)+ ")";
    }

    @Override
    public String saveFormat() {
       return "D | " + (super.isDone ? "1 | " : "0 | ") + description + " | "  + date;
    }

}
