package lict.task;

import lict.DateTime;
import lict.LictException;

import java.time.DateTimeException;

public class Event extends Task {
    protected DateTime from;
    protected DateTime to;

    public Event(String description, String from, String to) throws LictException {
        super(description);
        try {
            this.from = new DateTime(from);
            //If 'to' only contains time, assume that it has the same date as from
            if (to.matches("\\d{4}")) {
                String date = this.from.getData().split(" ")[0];
                this.to = new DateTime(date + " " + to);
            } else {
                this.to = new DateTime(to);
            }
        } catch (DateTimeException e) {
            throw new LictException("Invalid format for event start date or event end date. Please ensure that lict.task.Event date and time information is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.getString() + " to: " + this.to.getString() + ")";
    }

    @Override
    public String toData() {
        String status = this.isDone ? "1" : "0";
        return String.format("EVENT | %s | %s | %s | %s\n", status, this.description, this.from.getData(), this.to.getData());
    }

    public static Event loadTask(String dataMessage) throws LictException {
        String[] messageParts = dataMessage.split("\\|", 3);
        String description = messageParts[0].trim();
        if (description.isEmpty() || messageParts.length != 3) {
            throw new LictException();
        }
        return new Event(messageParts[0].trim(), messageParts[1].trim(), messageParts[2].trim());
    }
}
