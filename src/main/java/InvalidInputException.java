public class InvalidInputException extends Exception{
    public InvalidInputException() {
        super(String.format("OOPS!! I'm sorry, but I don't know what that means :-( %nEnter again please"));
    }
}
