public class InvalidStateException extends Exception {
    private String state;
    public InvalidStateException(String state) {
        super();
        this.state = state;
    }

    @Override
    public String toString() {
        return "State: " + state + " is invalid. Should be 1 or 0.";
    }
}
