public class NoInputException extends RuntimeException{
    public NoInputException() {
        super("I don't see your input anywhere. Are you sure you typed something?");
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

}
