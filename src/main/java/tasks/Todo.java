package tasks;
import chatterboxexceptions.ChatterboxExceptions;

/**
 * Todo subclass of Task that has changes task symbol
 */
public class Todo extends Task {


    /**
     * Constructor for Todo Object
     * @param desc String of desc
     * @throws ChatterboxExceptions.ChatterBoxNoInput if desc not found
     */
    public Todo(String desc) throws ChatterboxExceptions.ChatterBoxNoInput {
        super(desc);

    }

    @Override
    public String getTaskSymbol() {
        return "T";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + this.getTags();
    }

    @Override
    public String descNoTags() {
        return super.getDescription();
    }
}
