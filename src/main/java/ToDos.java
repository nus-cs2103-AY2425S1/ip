public class ToDos extends Task{

    public ToDos(String desc) throws EmptyDescException{
        super(desc);
        if (desc.equals("")) {
            //thow exception if not desc is given
            throw new EmptyDescException("     OOPS!!! The description of a todo cannot be empty leh.");
        }
    }

    @Override
    public String print() {
        return "[T]" + super.print();
    }
}
