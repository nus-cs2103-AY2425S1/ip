public class CheeseException extends Exception{
    CheeseException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Command not gouda.... " + super.getMessage();
    }
}
