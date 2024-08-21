public class HueException extends Exception {
    public HueException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "ERROR!!! " + super.getMessage() + "\n";
    }
}
