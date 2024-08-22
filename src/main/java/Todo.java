public class Todo extends Task{
    public Todo(String desc, Boolean mark) throws MissingDescriptionException{
        super(desc, mark);
    }

    @Override
    public String printTask() {
        return String.format("[T]%s", super.printTask());
    }
}
