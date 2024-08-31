package tasks;
import chatterboxexceptions.ChatterboxExceptions;
import parser.Parser;
import java.time.LocalDateTime;

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
    public Event(String desc, String startDate, String endDate) throws ChatterboxExceptions.ChatterBoxNoInput{
        super(desc);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateObj = null;
        this.endDateObj = null;
    }

    public Event(String desc, LocalDateTime startDateObj, LocalDateTime endDateObj) throws ChatterboxExceptions.ChatterBoxNoInput{
        super(desc);
        this.startDate = null;
        this.endDate = null;
        this.startDateObj = startDateObj;
        this.endDateObj = endDateObj;
    }

    public String getStartDate() {
        return startDate;
    }

    private String getEndDate() {
        return this.endDate;
    }

    @Override
    public String getTaskSymbol() {
        return "E";
    }
    @Override
    public String getDescription() {

        if (this.startDateObj != null && this.endDateObj != null) {
            return super.getDescription() + String.format(" ( from %s to %s )", this.startDateObj.format(parser.Parser.getPrintdateformatter())
                    , this.endDateObj.format(parser.Parser.getPrintdateformatter()));
        }
        return super.getDescription() + String.format(" ( from %s to %s )", this.startDate, this.endDate);
    }


}
