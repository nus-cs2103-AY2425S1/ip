public class WrongMessageException  extends Exception{
    public WrongMessageException() {
        super("Please enter your task in a proper format");
    }

    @Override
    public String toString() {
        return "Please enter your task in a proper format:(( \n";
    }
}
