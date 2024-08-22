public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public static ToDo getToDoFromInput(String input) {
        String name = input.substring(5);;

        return new ToDo(name);
    }


    @Override
    public String toString() { 
        return "[T]" + super.toString(); 
    }
}
