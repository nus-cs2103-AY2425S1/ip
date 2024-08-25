public class ToDos extends Task{
    public ToDos(String description){
        super(description);
    }
    public ToDos(String description,boolean status){
        super(description,status);
    }

    @Override
    public String toString(){
        return String.format("[T]%s",super.toString());
    }
}