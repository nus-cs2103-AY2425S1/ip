public class AliceException extends Exception{
    protected String message;
    public AliceException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OOPS! I don't know what '" + this.message + "' means!";
    }
}
