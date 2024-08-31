package tasks;
import chatterboxexceptions.ChatterboxExceptions;
import parser.Parser;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime dueDateObj;
    private final String dueDate;

    public Deadline(String desc, String dueDate) throws ChatterboxExceptions.ChatterBoxNoInput{
        super(desc);
        this.dueDate =dueDate;
        this.dueDateObj = null;


    }

    public Deadline(String desc, LocalDateTime dueDateObj) throws ChatterboxExceptions.ChatterBoxNoInput{
        super(desc);
        this.dueDateObj = dueDateObj;
        this.dueDate = null;
    }

    @Override
    public String getTaskSymbol() {
        return "D";
    }
    @Override
    public String getDescription() {
        if (this.dueDateObj != null) {
            return super.getDescription() + String.format("( by %s ) ", this.dueDateObj.format(parser.Parser.getPrintdateformatter()));
        }
        return super.getDescription() + String.format(" ( by %s )", this.dueDate);
    }

}
