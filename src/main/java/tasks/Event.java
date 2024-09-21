package tasks;

import java.time.LocalDateTime;

import chatterboxexceptions.ChatterboxExceptions;


/**
 * Event subclass of Task that has  a startDate and endDate, represented by either a String or LocalDateTime object
 */
public class Event extends Task {

    private final LocalDateTime startDateObj;
    private final String startDate;

    private final LocalDateTime endDateObj;
    private final String endDate;


    /**
     * Initializes Event Object with Strings
     * @param desc description of event
     * @param startDate String description of startDate
     * @param endDate String description of endDate
     * @throws ChatterboxExceptions.ChatterBoxNoInput if description is empty
     */
    public Event(String desc, String startDate, String endDate) throws ChatterboxExceptions.ChatterBoxNoInput {
        super(desc);
        if (startDate.isEmpty() || endDate.isEmpty()) {
            throw new ChatterboxExceptions.ChatterBoxNoInput("Missing input for Event");
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateObj = null;
        this.endDateObj = null;
    }

    /**
     * Initializes Event Object with LocalDateTime objects
     * @param desc description of event
     * @param startDateObj LocalDateTime of start time
     * @param endDateObj  LocalDateTime of end time
     * @throws ChatterboxExceptions.ChatterBoxNoInput
     */
    public Event(String desc, LocalDateTime startDateObj, LocalDateTime endDateObj)
            throws ChatterboxExceptions.ChatterBoxNoInput {
        super(desc);
        this.startDate = null;
        this.endDate = null;
        this.startDateObj = startDateObj;
        this.endDateObj = endDateObj;
    }


    @Override
    public String getTaskSymbol() {
        return "E";
    }
    @Override
    public String getDescription() {

        if (this.startDateObj != null && this.endDateObj != null) {
            return super.getDescription() + String.format("( from %s to %s ) " + this.getTags(),
                    this.startDateObj.format(parser.Parser.getPrintDateFormatter()),
                    this.endDateObj.format(parser.Parser.getPrintDateFormatter()));
        }
        return super.getDescription() + String.format("( from %s to %s ) "
                + this.getTags(), this.startDate, this.endDate);
    }


    @Override
    public String descNoTags() {
        if (this.startDateObj != null && this.endDateObj != null) {
            return super.getDescription() + String.format("( from %s to %s )",
                    this.startDateObj.format(parser.Parser.getPrintDateFormatter()),
                    this.endDateObj.format(parser.Parser.getPrintDateFormatter()));
        }
        return super.getDescription() + String.format("( from %s to %s )", this.startDate, this.endDate);
    }
}
