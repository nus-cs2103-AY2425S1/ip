public class ToDo extends Task{
    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[D]%s", super.toString());
    }
}
