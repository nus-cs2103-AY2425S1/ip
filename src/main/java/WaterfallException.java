public class WaterfallException extends Exception {
    public WaterfallException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return "Oops! " + super.toString() + " Water has fallen! :(";
    }
}
