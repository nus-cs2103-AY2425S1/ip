public class EmptyDescException extends Exception{
    public EmptyDescException(){
        super("Task Description cannot be empty");
    }
}
