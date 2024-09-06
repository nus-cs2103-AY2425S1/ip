package trackbot.task;

import trackbot.ui.Parser;

/**
 * Event task with a start and end time.
 */
public class Event extends Task {
    private String datefrom;
    private String dateto;

    /**
     * Constructs an Event task with the specified description, start time and end time.
     *
     * @param desc The description of the event.
     * @param datefrom The start time of the event.
     * @param dateto The end time of the event.
     */
    public Event(String desc, String datefrom, String dateto) {
        super(desc);
        this.datefrom = datefrom;
        this.dateto = dateto;
        this.datefrom = Parser.checkDateFormat(datefrom);
        this.dateto = Parser.checkDateFormat(dateto);
    }

    public String getDateFrom() {
        return datefrom;
    }

    public String getDateTo() {
        return dateto;
    }

    @Override
    public String toStorageFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + desc + " | " + datefrom + " | " + dateto;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + datefrom + " to: " + dateto + ")";
    }
}
