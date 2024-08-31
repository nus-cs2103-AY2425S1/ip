package tasks;
import chatterboxexceptions.ChatterboxExceptions;

/**
 * Todo subclass of Task that has changes task symbol
 */
public  class Todo extends Task {


    public Todo(String desc) throws ChatterboxExceptions.ChatterBoxNoInput{
        super(desc);

    }

    @Override
    public String getTaskSymbol() { return "T"; }

}
