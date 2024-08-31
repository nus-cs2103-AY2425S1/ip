package orion.tasks;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;
    public Event(String body, LocalDate start, LocalDate end) {
        super(body);
        this.start = start;
        this.end = end;
    }

    public Event(String body, String from, String to) throws OrionException {
        super(body);
        try {
            LocalDate start = LocalDate.parse(from);
            LocalDate end = LocalDate.parse(to);
            if (start.isAfter(end)) {
                throw new OrionInputException("Your start date must be later than your end date!");
            }
            this.start = start;
            this.end = end;
        } catch (DateTimeException e) {
            throw new OrionInputException("Correct syntax: event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>. " +
                    "Please input valid dates in the correct format!");
        }
    }

    public Event(String body, boolean isDone, LocalDate start, LocalDate end) {
        super(body, isDone);
        this.start = start;
        this.end = end;
    }

    private String getTimeString(LocalDate date) {
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), getTimeString(this.start), getTimeString(this.end));
    }

    @Override
    public String saveString() {
        return "event," + super.saveString() + "," + this.start + "," + this.end;
    }
}
