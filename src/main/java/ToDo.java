public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public static ToDo getToDoFromInput(String input) throws InvalidTaskFormatException {
        if (input.length() < 5) {
            throw new InvalidTaskFormatException("ToDo");
        }

        return new ToDo(input.substring(5));
    }


    @Override
    public String toString() { 
        return "[T]" + super.toString(); 
    }
}
