public class Todo extends Task{
    public Todo(String desc){
        super(desc);
    }

    @Override
    public String stringValue(){
        return ("[T]" + super.stringValue());
    }
}
