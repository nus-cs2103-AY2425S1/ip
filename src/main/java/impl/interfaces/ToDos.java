package impl.interfaces;

public class ToDos extends Task{
    /** @inheritDoc */
    public ToDos(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String loadString(){
        return "Todo " + this.description + " | " + this.isDone;
    }
}
