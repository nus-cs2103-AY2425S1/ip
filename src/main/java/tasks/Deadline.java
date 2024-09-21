package tasks;

import java.time.LocalDateTime;

import chatterboxexceptions.ChatterboxExceptions;

/**
 * Subclass of Task that handles a single deadline specification.
 */
public class Deadline extends Task {
    private final LocalDateTime dueDateObj;
    private final String dueDate;

    /**
     *  Constructor for Deadline using input string.
     *
     * @param desc description of the task.
     * @param dueDate input string deadline.
     * @throws ChatterboxExceptions.ChatterBoxNoInput
     */
    public Deadline(String desc, String dueDate) throws ChatterboxExceptions.ChatterBoxNoInput {
        super(desc);
        this.dueDate = dueDate;
        this.dueDateObj = null;
    }

    /**
     * Constructor for Deadline using LocalDateTime objects.
     *
     * @param desc description of the task.
     * @param dueDateObj LocalDateTime of deadline.
     * @throws ChatterboxExceptions.ChatterBoxNoInput if no desc found.
     */
    public Deadline(String desc, LocalDateTime dueDateObj) throws ChatterboxExceptions.ChatterBoxNoInput {
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
            return super.getDescription() + String.format("( by %s ) " + this.getTags(),
                    this.dueDateObj.format(parser.Parser.getPrintDateFormatter()));
        }
        return super.getDescription() + String.format("( by %s ) " + this.getTags(), this.dueDate);
    }

    @Override
    public String descNoTags() {
        if (this.dueDateObj != null) {
            return super.getDescription() + String.format("( by %s )",
                    this.dueDateObj.format(parser.Parser.getPrintDateFormatter()));
        }
        return super.getDescription() + String.format("( by %s )", this.dueDate);
    }

}
