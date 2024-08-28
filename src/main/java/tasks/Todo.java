package tasks;
import chatterboxexceptions.ChatterboxExceptions;
public  class Todo extends Task {


    public Todo(String desc) throws ChatterboxExceptions.ChatterBoxNoInput{
        super(desc);

    }

    @Override
    public String getTaskSymbol() { return "T"; }

}
